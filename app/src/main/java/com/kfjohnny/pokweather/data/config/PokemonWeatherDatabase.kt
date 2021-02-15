package com.kfjohnny.pokweather.data.config

import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kfjohnny.pokweather.data.PokemonSampleDAO
import com.kfjohnny.pokweather.model.search.PokemonSample

@Database(entities = [PokemonSample::class], version = 1)
abstract class PokemonWeatherDatabase : RoomDatabase() {

    abstract fun pokemonSampleDao() : PokemonSampleDAO
}