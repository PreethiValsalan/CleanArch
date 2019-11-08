package com.interapp.developer.datawrapper.dataSource

import com.interapp.developer.datawrapper.dto.PokemonWrapper
import com.interapp.developer.domain.IPokemonUsecase
import com.interapp.developer.domain.model.IPokemonWrapper

/**
Created by Preethi Valsalan on 2019-11-03
 */
class PokemonUseCase(val networkDataSource: IDataSource, val cacheDataSource: IDataSource)  :
    IPokemonUsecase {

    override suspend fun getPokemonListings(
        nextUrl: String,
        isNetworkAvailable: Boolean
    ): IPokemonWrapper {
        var dataSource = if(isNetworkAvailable) networkDataSource else cacheDataSource
        return dataSource.getPokemonListings(nextUrl)
    }

    override suspend fun getPokemonListings(
        page: Int,
        pageSize: Int,
        isNetworkAvailable: Boolean
    ): IPokemonWrapper {
        var dataSource = if(isNetworkAvailable) networkDataSource else cacheDataSource
        return dataSource.getPokemonListings(page, pageSize)
    }

}

interface IDataSource {
    suspend fun getPokemonListings(page : Int, pageSize: Int) : PokemonWrapper

    suspend fun getPokemonListings(nextUrl: String) : PokemonWrapper

}