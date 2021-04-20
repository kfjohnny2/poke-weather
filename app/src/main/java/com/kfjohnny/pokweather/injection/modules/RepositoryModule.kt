package com.kfjohnny.pokweather.injection.modules

import android.content.Context
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.kfjohnny.pokweather.BuildConfig.API_URL
import com.kfjohnny.pokweather.data.config.PokemonWeatherDatabase
import com.kfjohnny.pokweather.network.PokemonApi
import com.kfjohnny.pokweather.ui.description.DetailsViewModel
import com.kfjohnny.pokweather.ui.main.MainViewModel
import com.kfjohnny.pokweather.ui.main.repository.PokemonRepository
import com.kfjohnny.pokweather.ui.main.repository.PokemonRepositoryImpl
import com.kfjohnny.pokweather.ui.whodatpokemon.WhoDatPokemonViewModel
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
const val POKEMON_REPOSITORY = "pokemonRepository"
val repositoryModules = module {
    single {
        createWebService<PokemonApi>()
    }
    single {
        Room.databaseBuilder(get(), PokemonWeatherDatabase::class.java, "pokeweather-db")
            .build()
    }

    single { get<PokemonWeatherDatabase>().pokemonSampleDao() }

    single<PokemonRepository>(named(POKEMON_REPOSITORY)) {
        PokemonRepositoryImpl(
            pokemonApi = get(),
            pokemonSampleDAO = get()
        )
    }
    viewModel { MainViewModel(get(named(POKEMON_REPOSITORY))) }
    viewModel { DetailsViewModel(get(named(POKEMON_REPOSITORY))) }
    viewModel { WhoDatPokemonViewModel(get(named(POKEMON_REPOSITORY))) }
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

inline fun <reified T> createRoomDatabase(context: Context) {
    Room.databaseBuilder(context, PokemonWeatherDatabase::class.java, "pokeweather-db")
        .build()
}
