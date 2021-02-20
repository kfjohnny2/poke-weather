package com.kfjohnny.pokweather.ui.description

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kfjohnny.pokweather.base.BaseViewModel
import com.kfjohnny.pokweather.model.pokemon.Pokemon

class DetailsViewModel : BaseViewModel()  {
    val pokemonData = MutableLiveData<Pokemon>()


}