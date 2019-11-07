package com.kfjohnny.pokweather.model.location

import com.google.gson.annotations.SerializedName
import com.kfjohnny.pokweather.util.NAME_FIELD
import com.kfjohnny.pokweather.util.URL_FIELD

data class LocationArea(
    @SerializedName(NAME_FIELD)
    val locationAreaName : String,
    @SerializedName(URL_FIELD)
    val locationAreaUrl : String
)