package com.interapp.developer.datawrapper.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Scope

/**
Created by Preethi Valsalan on 2019-11-03
 */

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ApiScope

@ApiScope
@Subcomponent(modules = [ApiModule::class])
interface ApiComponent {

    fun pokemonService() : PokemonApi;

    @Subcomponent.Builder
    interface Builder {
        fun apiBuilder(apiProvider: ApiModule): Builder
        fun build(): ApiComponent
    }
}

@Module
class ApiModule(val baseUrl : String) {

    @Provides
    @ApiScope
    fun provideHttpLoggingInterceptor() : HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }


    @Provides
    @ApiScope
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor) : OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .build()
    }

    @Provides
    @ApiScope
    fun provideKotlinJsonBuilder() : KotlinJsonAdapterFactory {
        return KotlinJsonAdapterFactory();
    }

    @Provides
    @ApiScope
    fun provideJsonBuilder(kotlinJsonFactory: KotlinJsonAdapterFactory) : Moshi {
        return Moshi.Builder().add(kotlinJsonFactory).build();
    }

    @Provides
    @ApiScope
    fun provideCoverterFactory(jsonBuilder : Moshi) : MoshiConverterFactory {
        return MoshiConverterFactory.create(jsonBuilder);
    }

    @Provides
    @ApiScope
    fun provideRetrofit(converterFactory: MoshiConverterFactory, okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    @ApiScope
    fun providePokemonApi(retrofit: Retrofit) : PokemonApi{
        return retrofit.create(PokemonApi::class.java)
    }

}