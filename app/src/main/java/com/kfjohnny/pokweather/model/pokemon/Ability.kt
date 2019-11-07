package com.kfjohnny.pokweather.model.pokemon

import com.google.gson.annotations.SerializedName
import com.kfjohnny.pokweather.util.URL_FIELD
import com.kfjohnny.pokweather.util.IS_HIDDEN_FIELD
import com.kfjohnny.pokweather.util.NAME_FIELD

data class Ability(
    @SerializedName(NAME_FIELD)
    val abilityName : String,
    @SerializedName(URL_FIELD)
    val abilityUrl : String,
    @SerializedName(IS_HIDDEN_FIELD)
    val isHidden : Boolean,
    val slot : Int
)