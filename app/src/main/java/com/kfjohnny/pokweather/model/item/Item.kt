package com.kfjohnny.pokweather.model.item

import com.google.gson.annotations.SerializedName
import com.kfjohnny.pokweather.util.NAME_FIELD
import com.kfjohnny.pokweather.util.URL_FIELD

data class Item(
    @SerializedName(NAME_FIELD)
    val itemName : String,
    @SerializedName(URL_FIELD)
    val itemUrl : String
)