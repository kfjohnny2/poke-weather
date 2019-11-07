package com.kfjohnny.pokweather.model.types

import com.google.gson.annotations.SerializedName
import com.kfjohnny.pokweather.util.NAME_FIELD
import com.kfjohnny.pokweather.util.URL_FIELD

data class Type(
    @SerializedName(NAME_FIELD) val typeName : String,
    @SerializedName(URL_FIELD) val typeUrl : String
)
