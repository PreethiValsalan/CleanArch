package com.interapp.developer.datawrapper.dataSource

import com.interapp.developer.datawrapper.dataSource.cache.CacheDataSource
import com.interapp.developer.datawrapper.dataSource.network.NetworkDataSource
import com.interapp.developer.datawrapper.network.ApiComponent
import com.interapp.developer.datawrapper.network.ApiModule
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Scope

/**
Created by Preethi Valsalan on 2019-10-26
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class DataSourceScope {
}

@DataSourceScope
@Component(modules = [DataSourceProvider::class])
interface DataSourceComponent {

    fun pokemonUseCase() : PokemonUseCase

    @Component.Builder
    interface Builder {

        @BindsInstance
        abstract fun dataSourceModule(baseUrl : String): DataSourceComponent.Builder

        fun build(): DataSourceComponent
    }

}

@Module(subcomponents = [ApiComponent::class])
class DataSourceProvider {

    @Provides
    @DataSourceScope
    @Named("network")
    internal fun provideNetworkService(apiBuilder: ApiComponent.Builder, baseUrl: String): IDataSource {
        return NetworkDataSource(apiBuilder.apiBuilder(ApiModule(baseUrl)).build().pokemonService())
    }


    @Provides
    @DataSourceScope
    @Named("cache")
    internal fun provideCacheDataSource(): IDataSource {
        return CacheDataSource()
    }


    @Provides
    @DataSourceScope
    internal fun provideDataRetriever(@Named("network") networkDS: IDataSource, @Named("cache") cacheDS: IDataSource): PokemonUseCase {
        return PokemonUseCase(networkDS, cacheDS)
    }


}