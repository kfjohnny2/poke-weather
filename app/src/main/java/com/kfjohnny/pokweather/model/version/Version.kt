package com.kfjohnny.pokweather.model.version

import com.google.gson.annotations.SerializedName
import com.kfjohnny.pokweather.util.NAME_FIELD
import com.kfjohnny.pokweather.util.URL_FIELD

data class Version (
    @SerializedName(NAME_FIELD)
    val versionName: String,
    @SerializedName(URL_FIELD)
    val versionUrl : String
)
