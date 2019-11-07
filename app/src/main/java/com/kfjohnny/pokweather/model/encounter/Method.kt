package com.kfjohnny.pokweather.model.encounter

import com.google.gson.annotations.SerializedName
import com.kfjohnny.pokweather.util.NAME_FIELD
import com.kfjohnny.pokweather.util.URL_FIELD

data class Method(
    @SerializedName(NAME_FIELD)
    val methodName : String,
    @SerializedName(URL_FIELD)
    val methodUrl : String
)
