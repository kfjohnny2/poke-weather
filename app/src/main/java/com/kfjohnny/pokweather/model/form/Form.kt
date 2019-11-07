package com.kfjohnny.pokweather.model.form

import com.google.gson.annotations.SerializedName
import com.kfjohnny.pokweather.util.NAME_FIELD
import com.kfjohnny.pokweather.util.URL_FIELD

data class Form(
    @SerializedName(NAME_FIELD)
    val formName : String,
    @SerializedName(URL_FIELD)
    val formUrl : String
)


