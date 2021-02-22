package com.kfjohnny.pokweather.network

import com.kfjohnny.pokweather.model.pokemon.Pokemon
import com.kfjohnny.pokweather.model.search.SearchResult
import com.kfjohnny.pokweather.util.ID_FIELD
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {
    /**
     * Get the list of pokemons from the API
     */
    @GET("pokemon/")
    suspend fun getPokemons() : Response<SearchResult>

    /**
     * Get the pokemon from the API
     */
    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path(ID_FIELD) pokemonId: String) : Response<Pokemon>
}
