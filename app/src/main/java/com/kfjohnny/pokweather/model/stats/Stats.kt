package com.kfjohnny.pokweather.model.stats

import com.google.gson.annotations.SerializedName
import com.kfjohnny.pokweather.util.BASE_STAT_FIELD

data class Stats(
    @SerializedName(BASE_STAT_FIELD) val baseStat : Int,
    val effort : Int,
    val stat : Stat
)
