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

    /**
     * Search Pokemon Sample for the given name
     * @param sentence Name (or part of) of the Pokemon or straight by the ID (example: sentence = 25, returns Pikachu)
     * @return PokemonSample Pokemon Name and Url object
     */
    @Query("SELECT * FROM pokemon_sample where pokemonName like '%' || :sentence || '%'or `replace`(substr(pokemonUrl,35), '/', '') = :sentence")
    fun findPokemonByNameOrID(sentence: String): List<PokemonSample>

    /**
     * Search first pokemon on database (only for checking if there's data)
     * @return PokemonSample First pokemon
     */
    @Query("SELECT * FROM pokemon_sample LIMIT 1")
    fun findFirst(): PokemonSample

    /**
     * Search first pokemon on database (only for checking if there's data)
     * @return PokemonSample First pokemon
     */
    @Query("SELECT * FROM pokemon_sample LIMIT 100")
    fun getPokemonsLocal(): List<PokemonSample>
}