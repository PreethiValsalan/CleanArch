package com.interapp.developer.datawrapper.dto

import com.interapp.developer.domain.model.IPokemon
import com.interapp.developer.domain.model.IPokemonWrapper
import com.squareup.moshi.Json

/**
Created by Preethi Valsalan on 2019-11-03
 */

data class PokemonWrapper(

    @Json(name = "count") override val totalCount: Int,
    @Json(name = "next") override val next: String? = null,
    @Json(name = "previous") override val previous: String? = null,
    @Json(name = "results") override val pokemonList: List<PokemonEntity>
) : IPokemonWrapper

data class PokemonEntity(
    @Json(name = "name") override val name: String? = null,
    @Json(name = "url") override val url: String? = null
) : IPokemon