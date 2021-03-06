package com.kfjohnny.pokweather.ui.description.adapter

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kfjohnny.pokweather.base.BaseViewModel
import com.kfjohnny.pokweather.model.moves.Move
import com.kfjohnny.pokweather.util.extensions.upperCaseFirstCharacter

class MovesItemViewModel : BaseViewModel() {
    private val pokemonMoveName  = MutableLiveData<String>()

    @SuppressLint("DefaultLocale")
    fun bind(move: Move){
        pokemonMoveName.postValue(move.moveName.capitalize().replace("-", " "))
    }

    fun getMoveName() : LiveData<String>{
        return pokemonMoveName
    }
}