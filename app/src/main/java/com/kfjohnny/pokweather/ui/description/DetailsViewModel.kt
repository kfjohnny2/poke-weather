package com.kfjohnny.pokweather.ui.description

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.kfjohnny.pokweather.base.BaseViewModel
import com.kfjohnny.pokweather.base.UseCaseResult
import com.kfjohnny.pokweather.model.pokemon.Pokemon
import com.kfjohnny.pokweather.ui.main.repository.PokemonRepository
import com.kfjohnny.pokweather.util.enum_classes.PokemonTypeResourceEnum
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel(private val pokemonRepository: PokemonRepository) : BaseViewModel()  {
    val pokemonData = MutableLiveData<Pokemon>()
    private var filters: MutableList<PokemonTypeResourceEnum> = PokemonTypeResourceEnum.values().toMutableList()

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