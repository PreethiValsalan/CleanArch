package com.interapp.developer.domain

import com.interapp.developer.domain.model.IPokemonWrapper

/**
Created by Preethi Valsalan on 2019-11-03
 */    
interface IPokemonUsecase {

    suspend fun getPokemonListings(page: Int, pageSize: Int, isNetworkAvailable: Boolean): IPokemonWrapper;

    suspend fun getPokemonListings(nextUrl : String, isNetworkAvailable: Boolean): IPokemonWrapper;

}