package com.kfjohnny.pokweather.ui.main.repository

import com.kfjohnny.pokweather.base.UseCaseResult
import com.kfjohnny.pokweather.model.pokemon.Pokemon
import com.kfjohnny.pokweather.network.PokemonApi

class PokemonRepositoryImpl(private val pokemonApi: PokemonApi) : PokemonRepository {
    override suspend fun getPokemonList(): UseCaseResult<List<Pokemon>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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