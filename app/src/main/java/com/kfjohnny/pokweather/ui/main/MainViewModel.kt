package com.kfjohnny.pokweather.ui.main

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.kfjohnny.pokweather.base.BaseViewModel
import com.kfjohnny.pokweather.base.UseCaseResult
import com.kfjohnny.pokweather.model.pokemon.Pokemon
import com.kfjohnny.pokweather.model.search.PokemonSample
import com.kfjohnny.pokweather.model.search.SearchResult
import com.kfjohnny.pokweather.ui.main.repository.PokemonRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/* -- Constant Values --*/
const val LIMIT_ALL_POKEMONS = 9999

/**
 * @author Johnnylee Rocha 11/07/2019
 *
 * MainViewModel for fetching and hold UI-Related Data with lifecycle management
 *
 * @param pokemonRepository Repository for request data (API and Local) functions
 */
class MainViewModel(private val pokemonRepository: PokemonRepository) : BaseViewModel() {
    val pokemonData = MutableLiveData<Pokemon>()
    val pokemonList = MutableLiveData<List<PokemonSample>>()

    /**
     * Start view model calling fetching data for local database
     */
    init {
        fetchLocalPokemonData()
    }

    /**
     * Calls function from the repository with a query for pokemons listing
     */
    private fun loadPokemons(){
        showLoading.value = true
        launch {

            when(val localPokemon = withContext(Dispatchers.IO){pokemonRepository.getPokemonLocal()}){
                is UseCaseResult.Success ->{
                    pokemonList.value = localPokemon.data
                }
                is UseCaseResult.Error ->{
                    Log.d("ERROR POKEMONS: ", "No Pokemons")
                }
            }
        }
    }

    /**
     * Function for calling local database if there's data. Otherwise, calls API for getting data
     * and inserts into local database.
     */
    private fun fetchLocalPokemonData(){
        launch {
            // Try to get pokemons from local database
            when(val requestLocalResult = withContext(Dispatchers.IO) {pokemonRepository.getPokemonLocal()}){
                //If exists = return result
                is UseCaseResult.Success ->{
                    pokemonList.value = requestLocalResult.data
                }
                //If doesn't exists = uses go fetch pokemons from service
                is UseCaseResult.Empty ->{
                    val result = withContext(Dispatchers.IO) { pokemonRepository.getPokemonList(LIMIT_ALL_POKEMONS) }

                    showLoading.value = false

                    when (result) {
                        is UseCaseResult.Success -> {
                            withContext(Dispatchers.IO){pokemonRepository.insertPokemons(result.data.results)}
                            loadPokemons()
                            Log.d("DATA POKEMONS: ", result.data.results.toString())
                        }
                        is UseCaseResult.Error -> {
                            showError.value = result.exception.message
                            Log.d("ERROR POKEMONS: ", result.exception.message!!)
                        }
                        is UseCaseResult.Empty -> {
                            showError.value = result.emptyMessage
                            Log.d("NO RESULT: ", result.emptyMessage)
                        }
                    }

                }
                is UseCaseResult.Error -> {
                    showError.value = requestLocalResult.exception.message
                    Log.d("ERROR POKEMONS: ", requestLocalResult.exception.message!!)
                }
            }
        }
    }

    /**
     * Function for loading single pokemon by his exact name straight from the API
     */
    fun loadPokemon(pokemonSearch : String) {
        val search = pokemonSearch
        showLoading.value = true

        launch {
            val result = withContext(Dispatchers.IO) { pokemonRepository.getPokemon(search) }

            showLoading.value = false

            when (result) {
                is UseCaseResult.Success -> pokemonData.value = result.data
                is UseCaseResult.Error -> {
                    showError.value = result.exception.message
                    Log.d("ERROR", result.exception.message!!)
                }
                is UseCaseResult.Empty ->{
                    showError.value = result.emptyMessage
                    Log.d("NO RESULT: ", result.emptyMessage)
                }
            }
        }
    }

    /**
     * Function for executing query that returns list of pokémons that contains string input
     */
    fun findPokemons(pokemonSearch: String){
        val pokemonSearch = pokemonSearch
        if (pokemonSearch.isEmpty()){
            loadPokemons()
            return
        }
        showLoading.value = true
        launch {

            when(val localPokemon = withContext(Dispatchers.IO){pokemonRepository.findPokemonByName(pokemonSearch)}){
                is UseCaseResult.Success ->{
                    pokemonList.value = localPokemon.data
                }
                is UseCaseResult.Empty ->{
                    showError.value = localPokemon.emptyMessage
                }
                is UseCaseResult.Error ->{
                    Log.d("ERROR POKEMONS: ", localPokemon.exception.message!!)
                }
            }
        }
    }
}
