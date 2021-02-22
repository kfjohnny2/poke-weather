package com.kfjohnny.pokweather.ui.description.adapter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kfjohnny.pokweather.base.BaseViewModel
import com.kfjohnny.pokweather.model.moves.Move

class MovesItemViewModel : BaseViewModel() {
    private val pokemonMoveName  = MutableLiveData<String>()

    fun bind(move: Move){
        pokemonMoveName.postValue(move.moveName)
    }

    fun getMoveName() : LiveData<String>{
        return pokemonMoveName
    }
}