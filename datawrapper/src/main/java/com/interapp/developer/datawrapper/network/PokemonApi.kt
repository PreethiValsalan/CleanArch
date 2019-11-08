package com.interapp.developer.datawrapper.network

import com.interapp.developer.datawrapper.dto.PokemonWrapper
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import retrofit2.http.POST



/**
Created by Preethi Valsalan on 2019-11-03
 */
interface PokemonApi {


    @GET("?")
    suspend fun getAllPokemons(
        @Query("limit") pageSize: Int,
        @Query("offset") rowPosition: Int
    ): PokemonWrapper

    @GET
    suspend fun getPokemons(@Url url: String): PokemonWrapper

}