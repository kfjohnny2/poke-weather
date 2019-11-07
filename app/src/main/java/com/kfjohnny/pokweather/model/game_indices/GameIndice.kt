package com.kfjohnny.pokweather.model.game_indices

import com.google.gson.annotations.SerializedName
import com.kfjohnny.pokweather.model.version.Version
import com.kfjohnny.pokweather.util.GAME_INDEX_FIELD

data class GameIndice (
    @SerializedName(GAME_INDEX_FIELD)
    val gameIndex : Int,
    val version : Version
)
