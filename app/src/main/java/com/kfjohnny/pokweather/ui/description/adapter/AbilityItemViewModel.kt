package com.kfjohnny.pokweather.ui.description.adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kfjohnny.pokweather.base.BaseViewModel
import com.kfjohnny.pokweather.model.pokemon.Ability

class AbilityItemViewModel : BaseViewModel() {
    private val pokemonAbilityName  = MutableLiveData<String>()

    fun bind(ability: Ability){
        pokemonAbilityName.postValue(ability.abilityName)
    }

    fun getAbilityName() : LiveData<String>{
        return pokemonAbilityName
    }
}