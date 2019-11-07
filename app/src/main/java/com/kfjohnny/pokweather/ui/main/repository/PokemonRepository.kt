package com.kfjohnny.pokweather.ui.main.repository

import com.kfjohnny.pokweather.base.UseCaseResult
import com.kfjohnny.pokweather.model.pokemon.Pokemon
import com.kfjohnny.pokweather.util.ID_FIELD
import retrofit2.http.Path

interface PokemonRepository{

    suspend fun getPokemon(@Path(ID_FIELD) pokemonId : Int) : UseCaseResult<Pokemon>
}