package com.kfjohnny.pokweather.ui.main.adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kfjohnny.pokweather.base.BaseViewModel
import com.kfjohnny.pokweather.model.moves.Move
import com.kfjohnny.pokweather.model.search.PokemonSample
import com.kfjohnny.pokweather.model.search.SearchResult
import com.kfjohnny.pokweather.util.extensions.upperCaseFirstCharacter

class PokemonGridItemViewModel : BaseViewModel() {
    private val pokemonId = MutableLiveData<String>()

    fun bind(pokemon: PokemonSample){
        pokemonId.postValue(getPokemonIdFromUrl(pokemon))
    }

    private fun getPokemonIdFromUrl(pokemon: PokemonSample): String {
        var src = pokemon.pokemonUrl
        src = src.removeRange(src.length - 1, src.length)
        val indexFirstSlash = src.lastIndexOf("/")
        src = src.substring(indexFirstSlash + 1)
        return src
    }

    fun getPokemonId() : LiveData<String> {
        return pokemonId
    }
}