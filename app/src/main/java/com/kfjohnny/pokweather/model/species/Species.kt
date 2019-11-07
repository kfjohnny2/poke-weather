package com.kfjohnny.pokweather.model.species

import com.google.gson.annotations.SerializedName
import com.kfjohnny.pokweather.util.NAME_FIELD
import com.kfjohnny.pokweather.util.URL_FIELD

data class Species(
    @SerializedName(NAME_FIELD) val speciesName : String,
    @SerializedName(URL_FIELD) val speciesUrl : String
)
