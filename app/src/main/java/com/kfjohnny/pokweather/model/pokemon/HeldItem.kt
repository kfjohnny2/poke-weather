package com.kfjohnny.pokweather.model.pokemon

import com.google.gson.annotations.SerializedName
import com.kfjohnny.pokweather.model.item.Item
import com.kfjohnny.pokweather.model.version.ItemVersionDetail
import com.kfjohnny.pokweather.util.VERSION_DETAILS_FIELD

data class HeldItem(
    val item : Item,
    @SerializedName(VERSION_DETAILS_FIELD)
    val itemVersionDetails : List<ItemVersionDetail>
)
