package com.interapp.developer.cleanarch

import android.content.Context
import com.interapp.developer.datawrapper.dataSource.DataSourceComponent
import com.interapp.developer.domain.IPokemonUsecase
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import com.interapp.developer.cleanarch.MainApplication
import javax.inject.Singleton

/**
Created by Preethi Valsalan on 2019-10-26
 */
@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppProvider::class, ActivityBuilder::class])
interface AppComponent : AndroidInjector<MainApplication> {

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun usecaseComponent(component: DataSourceComponent): Builder

        @BindsInstance
        fun application(application: MainApplication): Builder
    }
}

@Module
class AppProvider {

    @Provides
    @Singleton
    fun provideApplicationContext(application: MainApplication): Context {
        return application.applicationContext
    }


    @Provides
    @Singleton
    fun provideUseCase(component: DataSourceComponent): IPokemonUsecase {
        return component.pokemonUseCase()
    }
}