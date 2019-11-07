package com.kfjohnny.pokweather.model.version

import com.google.gson.annotations.SerializedName
import com.kfjohnny.pokweather.model.encounter.EncounterDetail
import com.kfjohnny.pokweather.util.ENCOUNTER_DETAILS_FIELD
import com.kfjohnny.pokweather.util.MAX_CHANCE_FIELD

data class LocationVersionDetail(
    @SerializedName(MAX_CHANCE_FIELD)
    val maxChance : Int,
    @SerializedName(ENCOUNTER_DETAILS_FIELD)
    val encounterDetails : List<EncounterDetail>,
    val version: Version
)
