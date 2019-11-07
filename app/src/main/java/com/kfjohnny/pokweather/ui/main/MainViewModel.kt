package com.kfjohnny.pokweather.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kfjohnny.pokweather.base.UseCaseResult
import com.kfjohnny.pokweather.model.pokemon.Pokemon
import com.kfjohnny.pokweather.ui.main.repository.PokemonRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainViewModel(private val pokemonRepository: PokemonRepository) : ViewModel(), CoroutineScope{
    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    val showLoading = MutableLiveData<Boolean>()
    val pokemonData = MutableLiveData<Pokemon>()
    val showError = MutableLiveData<String>()

    fun loadPokemon(){
        showLoading.value = true

        launch {
            val result = withContext(Dispatchers.IO){pokemonRepository.getPokemon("1")}

            showLoading.value = false

            when(result){
                is UseCaseResult.Success -> pokemonData.value = result.data
                is UseCaseResult.Error -> {
                    showError.value = result.exception.message
                    Log.d("ERROR", result.exception.message!!)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        // Clear our job when the linked activity is destroyed to avoid memory leaks
        job.cancel()
    }
}
