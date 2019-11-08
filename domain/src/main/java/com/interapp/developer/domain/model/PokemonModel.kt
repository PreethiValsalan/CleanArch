package com.interapp.developer.domain.model

/**
Created by Preethi Valsalan on 2019-11-03
 */    
interface IPokemonWrapper {
    val totalCount: Int
    val next: String?
    val previous: String?
    val pokemonList: List<IPokemon>
}

interface IPokemon {
    val name: String?
    val url: String?
}