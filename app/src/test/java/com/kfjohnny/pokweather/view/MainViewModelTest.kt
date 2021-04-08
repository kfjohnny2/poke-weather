package com.kfjohnny.pokweather.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.kfjohnny.pokweather.base.UseCaseResult
import com.kfjohnny.pokweather.injection.modules.repositoryModules
import com.kfjohnny.pokweather.injection.modules.roomModule
import com.kfjohnny.pokweather.model.pokemon.Pokemon
import com.kfjohnny.pokweather.ui.main.MainViewModel
import com.kfjohnny.pokweather.ui.main.repository.PokemonRepository
import com.kfjohnny.pokweather.ui.main.repository.PokemonRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.test.AutoCloseKoinTest
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.Mock
import org.mockito.Mockito
class MainViewModelTest : AutoCloseKoinTest() {
    private val repository : PokemonRepository by inject(named("pokemonRepository"))
    private val viewModel :  MainViewModel by inject()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<Pokemon>

    @Before
    fun before() {
        startKoin {
            loadKoinModules(repositoryModules)
        }
        declareMock<PokemonRepositoryImpl>()
    }

    @Test
    fun testGetPokemon() {
        val pokemon = runBlocking { repository.getPokemon("1") }
        viewModel.pokemonData.observeForever(observer)

        viewModel.loadPokemon("1")

        Assert.assertNotNull(viewModel.pokemonData.value)

        when (pokemon) {
            is UseCaseResult.Success -> Mockito.verify(observer).onChanged(pokemon.data)
        }

    }
}