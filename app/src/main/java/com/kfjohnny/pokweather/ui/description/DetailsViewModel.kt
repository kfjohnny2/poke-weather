package com.kfjohnny.pokweather.ui.description

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.kfjohnny.pokweather.base.BaseViewModel
import com.kfjohnny.pokweather.base.UseCaseResult
import com.kfjohnny.pokweather.model.pokemon.Pokemon
import com.kfjohnny.pokweather.ui.main.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel(private val pokemonRepository: PokemonRepository) : BaseViewModel()  {
    /* Live data for observers */
    val pokemonData = MutableLiveData<Pokemon>()

    /**
     * Loads pokemon details from itemClicked on MainFragment's RecyclerViewAdapter
     *
     * @param pokemonId PokemonID for retrieving details
     */
    fun loadPokemon(pokemonId : String) {
        val id = pokemonId
        showLoading.value = true

        launch {
            val result = withContext(Dispatchers.IO) { pokemonRepository.getPokemon(id) }

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