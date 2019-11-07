package com.kfjohnny.pokweather.injection.modules

import com.google.gson.GsonBuilder
import com.kfjohnny.pokweather.BuildConfig.API_URL
import com.kfjohnny.pokweather.network.PokemonApi
import com.kfjohnny.pokweather.ui.main.MainViewModel
import com.kfjohnny.pokweather.ui.main.repository.PokemonRepository
import com.kfjohnny.pokweather.ui.main.repository.PokemonRepositoryImpl
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModules = module {
    single {
        createWebService<PokemonApi>()
    }
    factory<PokemonRepository> { PokemonRepositoryImpl(pokemonApi = get()) }
    viewModel { MainViewModel(pokemonRepository = get()) }
}

/* Returns a custom OkHttpClient instance with interceptor. Used for building Retrofit service */
fun createHttpClient(): OkHttpClient {
    val client = OkHttpClient.Builder()
    client.readTimeout(5 * 60, TimeUnit.SECONDS)
    return client.addInterceptor {
        val original = it.request()
        val requestBuilder = original.newBuilder()
        requestBuilder.header("Content-Type", "application/json")
        val request = requestBuilder.method(original.method(), original.body()).build()
        return@addInterceptor it.proceed(request)
    }.build()
}

/* function to build our Retrofit service */
inline fun <reified T> createWebService(): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(API_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .client(createHttpClient())
        .build()
    return retrofit.create(T::class.java)
}