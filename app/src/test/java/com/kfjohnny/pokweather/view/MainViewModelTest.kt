package com.kfjohnny.pokweather.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.kfjohnny.pokweather.TestCoroutineRule
import com.kfjohnny.pokweather.base.UseCaseResult
import com.kfjohnny.pokweather.data.PokemonSampleDAO
import com.kfjohnny.pokweather.injection.modules.repositoryModules
import com.kfjohnny.pokweather.injection.modules.roomModule
import com.kfjohnny.pokweather.model.pokemon.Pokemon
import com.kfjohnny.pokweather.model.search.PokemonSample
import com.kfjohnny.pokweather.model.search.SearchResult
import com.kfjohnny.pokweather.network.PokemonApi
import com.kfjohnny.pokweather.ui.main.MainViewModel
import com.kfjohnny.pokweather.ui.main.repository.PokemonRepository
import com.kfjohnny.pokweather.ui.main.repository.PokemonRepositoryImpl
import kotlinx.coroutines.*
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
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
import org.mockito.MockitoAnnotations
import retrofit2.Response

@ExperimentalCoroutinesApi
class MainViewModelTest : AutoCloseKoinTest() {
    //private val repository : PokemonRepository by inject(named("pokemonRepository"))
    //private val viewModel :  MainViewModel by inject()

    @get:Rule
    val rule = InstantTaskExecutorRule()


    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var observer: Observer<Pokemon>

    @Mock
    private lateinit var pokemonApi: PokemonApi

    @Mock
    private lateinit var pokemonSampleDAO: PokemonSampleDAO

    @Before
    fun before() {
        MockitoAnnotations.openMocks(this)
        /*startKoin {
            loadKoinModules(repositoryModules)
        }
        declareMock<PokemonRepositoryImpl>()*/
    }

    @Test
    fun testGetPokemon() {
        testCoroutineRule.runBlockingTest {
            Mockito.`when`(pokemonSampleDAO.getPokemonsLocal()).thenReturn(
                listOf(PokemonSample(0, "pokemonName", "pokemonUrl"))
            )
        }
        val repository = PokemonRepositoryImpl(pokemonApi, pokemonSampleDAO)

        val viewModel = MainViewModel(repository, Dispatchers.Main)
        Assert.assertThat(viewModel.pokemonList.value?.get(0), `is`(PokemonSample(0, "pokemonName", "pokemonUrl")))
    }
}