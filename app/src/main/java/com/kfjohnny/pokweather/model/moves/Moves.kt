package com.kfjohnny.pokweather.model.moves

import com.google.gson.annotations.SerializedName
import com.kfjohnny.pokweather.model.version.VersionGroupDetails
import com.kfjohnny.pokweather.util.VERSION_GROUP_DETAILS_FIELD

data class Moves(
    val move : Move,
    @SerializedName(VERSION_GROUP_DETAILS_FIELD) val versionGroupDetails : List<VersionGroupDetails>
)
