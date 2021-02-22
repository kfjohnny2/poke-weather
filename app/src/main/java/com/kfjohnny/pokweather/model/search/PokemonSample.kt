package com.kfjohnny.pokweather.model.search

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.kfjohnny.pokweather.util.ID_FIELD
import com.kfjohnny.pokweather.util.NAME_FIELD
import com.kfjohnny.pokweather.util.URL_FIELD

@Entity(tableName = "pokemon_sample")
data class PokemonSample (
    @PrimaryKey(autoGenerate = true)
    val sampleId : Int,
    @SerializedName(NAME_FIELD)
    val pokemonName : String,
    @SerializedName(URL_FIELD)
    val pokemonUrl : String
)