package com.kfjohnny.pokweather.ui.main.repository

import com.kfjohnny.pokweather.base.UseCaseResult
import com.kfjohnny.pokweather.data.PokemonSampleDAO
import com.kfjohnny.pokweather.model.pokemon.Pokemon
import com.kfjohnny.pokweather.model.search.PokemonSample
import com.kfjohnny.pokweather.model.search.SearchResult
import com.kfjohnny.pokweather.network.PokemonApi

class PokemonRepositoryImpl(val pokemonApi: PokemonApi, val pokemonSampleDAO: PokemonSampleDAO) : PokemonRepository {
    override suspend fun insertPokemons(pokemonSampleList: List<PokemonSample>) {
        pokemonSampleDAO.saveAll(pokemonSampleList)
    }

    override suspend fun getPokemonList(): UseCaseResult<SearchResult> {
        return try{
            val result = pokemonApi.getPokemons()
            if (result.isSuccessful){
                UseCaseResult.Success(result.body()!!)
            } else{
                UseCaseResult.Error(Throwable(result.message()))
            }
        }catch (ex : Exception){
            UseCaseResult.Error(ex)
        }
    }

    override suspend fun getPokemon(pokemonId: String): UseCaseResult<Pokemon> {
        return try{
            val result = pokemonApi.getPokemon(pokemonId)
            if (result.isSuccessful){
                UseCaseResult.Success(result.body()!!)
            } else{
                UseCaseResult.Error(Throwable(result.message()))
            }
        }catch (ex : Exception){
            UseCaseResult.Error(ex)
        }
    }
}