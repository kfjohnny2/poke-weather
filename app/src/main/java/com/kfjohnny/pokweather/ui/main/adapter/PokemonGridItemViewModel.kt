package com.kfjohnny.pokweather.ui.main.adapter

import androidx.lifecycle.MutableLiveData
import com.kfjohnny.pokweather.base.BaseViewModel
import com.kfjohnny.pokweather.model.search.PokemonSample
import com.kfjohnny.pokweather.util.extensions.upperCaseFirstCharacter

open class PokemonGridItemViewModel : BaseViewModel() {
    private val pokemonId = MutableLiveData<String>()
    open val pokemonName = MutableLiveData<String>()

    fun bind(pokemon: PokemonSample){
        pokemonId.value = getPokemonIdFromUrl(pokemon)
        pokemonName.value = pokemon.pokemonName.upperCaseFirstCharacter()
    }

    private fun getPokemonIdFromUrl(pokemon: PokemonSample): String {
        var src = pokemon.pokemonUrl
        src = src.removeRange(src.length - 1, src.length)
        val indexOfLastSlash = src.lastIndexOf("/")
        src = src.substring(indexOfLastSlash + 1)
        return src
    }

    fun getPokemonId() : String {
        return pokemonId.value!!
    }
}