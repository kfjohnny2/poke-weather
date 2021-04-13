package com.kfjohnny.pokweather.ui.main.repository

import com.kfjohnny.pokweather.base.UseCaseResult
import com.kfjohnny.pokweather.model.pokemon.Pokemon
import com.kfjohnny.pokweather.model.search.PokemonSample
import com.kfjohnny.pokweather.model.search.SearchResult
import com.kfjohnny.pokweather.util.ID_FIELD
import retrofit2.http.Path

interface PokemonRepository{

    suspend fun getPokemon(@Path(ID_FIELD) pokemonId: String) : UseCaseResult<Pokemon>

    suspend fun getPokemonList() : UseCaseResult<SearchResult>

    suspend fun getPokemonList(limit : Int?) : UseCaseResult<SearchResult>

    suspend fun insertPokemons(pokemonSampleList: List<PokemonSample>)

    suspend fun getPokemonLocal(): UseCaseResult<List<PokemonSample>>

    suspend fun findPokemonByName(search : String): UseCaseResult<List<PokemonSample>>
}