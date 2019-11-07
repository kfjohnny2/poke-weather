package com.kfjohnny.pokweather.model.moves

import com.google.gson.annotations.SerializedName
import com.kfjohnny.pokweather.util.NAME_FIELD
import com.kfjohnny.pokweather.util.URL_FIELD

data class Move(

    @SerializedName(NAME_FIELD) val moveName: String,
    @SerializedName(URL_FIELD) val moveUrl: String
)
