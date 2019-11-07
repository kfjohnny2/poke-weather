package com.kfjohnny.pokweather.network

import com.kfjohnny.pokweather.model.pokemon.Pokemon
import com.kfjohnny.pokweather.util.ID_FIELD
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {
    /**
     * Get the pokemon from the API
     */
    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path(ID_FIELD) pokemonId : Int) : Response<Pokemon>
}
