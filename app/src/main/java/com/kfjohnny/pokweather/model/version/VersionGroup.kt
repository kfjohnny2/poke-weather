package com.kfjohnny.pokweather.model.version

import com.google.gson.annotations.SerializedName
import com.kfjohnny.pokweather.util.NAME_FIELD
import com.kfjohnny.pokweather.util.URL_FIELD

data class VersionGroup(
    @SerializedName(NAME_FIELD) val versionGroupName: String,
    @SerializedName(URL_FIELD) val versionGroupUrl: String

)
