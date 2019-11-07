package com.kfjohnny.pokweather.model.stats

import com.google.gson.annotations.SerializedName
import com.kfjohnny.pokweather.util.NAME_FIELD
import com.kfjohnny.pokweather.util.URL_FIELD

data class Stat(
    @SerializedName(NAME_FIELD) val statName : String,
    @SerializedName(URL_FIELD) val statUrl : String
)
