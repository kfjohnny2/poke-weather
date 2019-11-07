package com.kfjohnny.pokweather.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.kfjohnny.pokweather.base.UseCaseResult
import com.kfjohnny.pokweather.injection.modules.networkModules
import com.kfjohnny.pokweather.model.pokemon.Pokemon
import com.kfjohnny.pokweather.network.PokemonApi
import com.kfjohnny.pokweather.ui.main.MainViewModel
import com.kfjohnny.pokweather.ui.main.repository.PokemonRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainViewModelTest : KoinTest {
    private val viewModel by inject<MainViewModel>()
    private val repository by inject<PokemonRepository>()

    @Mock
    lateinit var uiData: Observer<Pokemon>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        startKoin {
            modules(networkModules)
        }
    }

    @Test
    fun testGetPokemon() {
        val pokemon = runBlocking { repository.getPokemon(1) }
        viewModel.pokemonData.observeForever(uiData)

        viewModel.loadPokemon()

        Assert.assertNotNull(viewModel.pokemonData.value)

        when (pokemon) {
            is UseCaseResult.Success -> Mockito.verify(uiData).onChanged(pokemon.data)
        }

    }
}