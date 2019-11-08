package com.interapp.developer.datawrapper.dataSource.network

import com.interapp.developer.datawrapper.dataSource.IDataSource
import com.interapp.developer.datawrapper.dto.PokemonWrapper
import com.interapp.developer.datawrapper.network.PokemonApi

/**
 * Class handling network calls
Created by Preethi Valsalan on 2019-10-26
 */
class NetworkDataSource(val service : PokemonApi) : IDataSource {

    override suspend fun getPokemonListings(nextUrl: String): PokemonWrapper {
        return service.getPokemons(nextUrl)
    }

    override suspend fun getPokemonListings(page: Int, pageSize: Int): PokemonWrapper {
        return service.getAllPokemons(page, pageSize)
    }
}