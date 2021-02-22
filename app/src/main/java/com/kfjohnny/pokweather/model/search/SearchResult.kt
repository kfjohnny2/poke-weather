package com.kfjohnny.pokweather.model.search

data class SearchResult (
    val count : Int,
    val next : String,
    val previous : String,
    val results : List<PokemonSample>
)