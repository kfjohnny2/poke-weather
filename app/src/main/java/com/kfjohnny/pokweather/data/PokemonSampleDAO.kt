package com.kfjohnny.pokweather.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kfjohnny.pokweather.model.search.PokemonSample
import io.reactivex.Single

@Dao
interface PokemonSampleDAO{

    /**
     * Save pokemon samples
     */
    @Insert
    fun saveAll(entities: List<PokemonSample>)

    /**
     * Search Pokemon Sample for the given name
     * @param name Name of the Pokemon
     * @return PokemonSample Pokemon Name and Url object
     */
    @Query("SELECT * FROM pokemon_sample WHERE pokemonName = :name")
    fun findPokemonByName(name: String): PokemonSample
}