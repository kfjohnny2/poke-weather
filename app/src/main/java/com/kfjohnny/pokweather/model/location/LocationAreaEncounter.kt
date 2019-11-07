package com.kfjohnny.pokweather.model.location

import com.google.gson.annotations.SerializedName
import com.kfjohnny.pokweather.model.version.LocationVersionDetail
import com.kfjohnny.pokweather.util.LOCATION_AREA_FIELD
import com.kfjohnny.pokweather.util.VERSION_DETAILS_FIELD

data class LocationAreaEncounter(
    @SerializedName(LOCATION_AREA_FIELD)
    val locationArea : LocationArea,
    @SerializedName(VERSION_DETAILS_FIELD)
    val locationVersionDetails : List<LocationVersionDetail>
)
