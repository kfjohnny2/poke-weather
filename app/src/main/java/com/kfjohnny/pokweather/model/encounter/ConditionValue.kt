package com.kfjohnny.pokweather.model.encounter

import com.google.gson.annotations.SerializedName
import com.kfjohnny.pokweather.util.NAME_FIELD
import com.kfjohnny.pokweather.util.URL_FIELD

data class ConditionValue(
    @SerializedName(NAME_FIELD)
    val conditionValueName : String,
    @SerializedName(URL_FIELD)
    val conditionValueUrl : String
)