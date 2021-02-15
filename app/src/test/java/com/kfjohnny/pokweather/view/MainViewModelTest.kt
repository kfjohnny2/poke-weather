package com.kfjohnny.pokweather.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.kfjohnny.pokweather.base.UseCaseResult
import com.kfjohnny.pokweather.injection.modules.repositoryModules
import com.kfjohnny.pokweather.model.pokemon.Pokemon
import com.kfjohnny.pokweather.ui.main.MainViewModel
import com.kfjohnny.pokweather.ui.main.repository.PokemonRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.Mockito

class MainViewModelTest : KoinTest {
    private val viewModel by inject<MainViewModel>()
    private val repository by inject<PokemonRepository>()

    @Mock
    lateinit var uiData: Observer<Pokemon>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun before() {
        startKoin {
            modules(repositoryModules)
        }
    }

    @Test
    fun testGetPokemon() {
        val pokemon = runBlocking { repository.getPokemon("1") }
        viewModel.pokemonData.observeForever(uiData)

        viewModel.loadPokemon("1")

        Assert.assertNotNull(viewModel.pokemonData.value)

        when (pokemon) {
            is UseCaseResult.Success -> Mockito.verify(uiData).onChanged(pokemon.data)
        }

    }
}