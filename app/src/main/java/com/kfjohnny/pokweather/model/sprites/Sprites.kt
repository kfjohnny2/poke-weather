package com.kfjohnny.pokweather.model.sprites

import com.google.gson.annotations.SerializedName
import com.kfjohnny.pokweather.util.*

data class Sprites(
    @SerializedName(BACK_FEMALE_FIELD) val backFemale : String,
    @SerializedName(BACK_SHINY_FEMALE_FIELD) val backShinyFemale : String,
    @SerializedName(BACK_DEFAULT_FIELD) val backDefault : String,
    @SerializedName(FRONT_FEMALE_FIELD) val frontFemale : String,
    @SerializedName(FRONT_SHINY_FEMALE_FIELD) val frontShinyFemale : String,
    @SerializedName(BACK_SHINY_FIELD) val backShiny : String,
    @SerializedName(FRONT_DEFAULT_FIELD) val frontDefault : String,
    @SerializedName(FRONT_SHINY_FIELD) val frontShiny : String

)
