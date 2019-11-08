package com.interapp.developer.datawrapper.dataSource.cache

import com.interapp.developer.datawrapper.dataSource.IDataSource
import com.interapp.developer.datawrapper.dto.PokemonWrapper
/**
Created by Preethi Valsalan on 2019-10-26
 */
class CacheDataSource : IDataSource {

    override suspend fun getPokemonListings(nextUrl: String): PokemonWrapper {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getPokemonListings(page: Int, pageSize: Int): PokemonWrapper {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}