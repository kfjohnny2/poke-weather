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

class MainViewModel(private val pokemonRepository: PokemonRepository) : BaseViewModel() {
    val pokemonData = MutableLiveData<Pokemon>()
    val pokemonList = MutableLiveData<List<PokemonSample>>()

    init {
        fetchLocalPokemonData()
    }


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

    private fun fetchLocalPokemonData(){
        launch {
            // Try to get pokemons from local database
            when(withContext(Dispatchers.IO) {pokemonRepository.getPokemonLocal()}){
                //If exists = uses loadPokemons from local data
                is UseCaseResult.Success ->{
                    loadPokemons()
                }
                //If doesn't exists = uses go fetch pokemons from service
                is UseCaseResult.Error ->{
                    val result = withContext(Dispatchers.IO) { pokemonRepository.getPokemonList(9999) }

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
                    }

                }
            }
        }
    }

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
            }
        }
    }

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
                is UseCaseResult.Error ->{
                    Log.d("ERROR POKEMONS: ", "No Pokemons")
                }
            }
        }
    }
}
