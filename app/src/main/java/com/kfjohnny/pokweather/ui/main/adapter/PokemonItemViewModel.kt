package com.kfjohnny.pokweather.ui.main.adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kfjohnny.pokweather.base.BaseViewModel
import com.kfjohnny.pokweather.model.moves.Move
import com.kfjohnny.pokweather.model.search.PokemonSample
import com.kfjohnny.pokweather.util.extensions.upperCaseFirstCharacter

class PokemonItemViewModel : BaseViewModel() {
    private val pokemonName  = MutableLiveData<String>()

    fun bind(pokemonSample: PokemonSample){
        pokemonName.postValue(pokemonSample.pokemonName.upperCaseFirstCharacter())
    }

    fun getPokemonName() : LiveData<String> {
        return pokemonName
    }
}