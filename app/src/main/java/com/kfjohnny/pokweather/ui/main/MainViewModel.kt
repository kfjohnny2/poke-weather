package com.kfjohnny.pokweather.ui.main

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.kfjohnny.pokweather.base.BaseViewModel
import com.kfjohnny.pokweather.base.UseCaseResult
import com.kfjohnny.pokweather.model.pokemon.Pokemon
import com.kfjohnny.pokweather.model.search.SearchResult
import com.kfjohnny.pokweather.ui.main.repository.PokemonRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainViewModel(val pokemonRepository: PokemonRepository) : BaseViewModel() {
    val pokemonData = MutableLiveData<Pokemon>()
    val pokemonsData = MutableLiveData<SearchResult>()

    init {
        loadPokemons()
    }

    fun loadPokemons(){
        showLoading.value = true

        launch {
            val result = withContext(Dispatchers.IO) { pokemonRepository.getPokemonList() }

            showLoading.value = false

            when (result) {
                is UseCaseResult.Success -> {
                    pokemonsData.value = result.data
                    withContext(Dispatchers.IO){pokemonRepository.insertPokemons(result.data.results)}
                    Log.d("DATA", result.data.results.toString())
                }
                is UseCaseResult.Error -> {
                    showError.value = result.exception.message
                    Log.d("ERROR", result.exception.message!!)
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
}
