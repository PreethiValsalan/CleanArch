package com.interapp.developer.cleanarch.mainList

import androidx.lifecycle.MutableLiveData
import com.interapp.developer.cleanarch.MainApplication
import com.interapp.developer.cleanarch.ViewModelFactory
import com.interapp.developer.cleanarch.paging.ListingsPagingDataSourceFactory
import com.interapp.developer.domain.IPokemonUsecase
import com.interapp.developer.domain.model.IPokemon
import com.interapp.developer.domain.model.Response
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Scope

/**
 * Classes to provide Dagger Componenets and Modules
Created by Preethi Valsalan on 2019-10-26
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class MainListUIScope {
}

/**
 * Dependency Provider class for ClosedListingsUI
 */
@Module
class MainListUIProvider {

    @Provides
    @MainListUIScope
    fun providesViewModelJob() : Job {
        return Job()
    }

    @Provides
    @MainListUIScope
    fun providesCoroutineScope(viewModelJob : Job) : CoroutineScope {
        return CoroutineScope(Dispatchers.Main + viewModelJob)
    }

    @Provides
    @MainListUIScope
    fun provideMainListPageAdapter() : MainListPageAdapter {
        return MainListPageAdapter()
    }

    @Provides
    @MainListUIScope
    fun provideErrorState() : MutableLiveData<Response> {
        return MutableLiveData<Response>()
    }

    @Provides
    @MainListUIScope
    fun provideListingsPagingDataSourceFactory(useCase: IPokemonUsecase,
                                               errorState: MutableLiveData<Response>,
                                               uiScope: CoroutineScope,
                                               application: MainApplication
    ) : ListingsPagingDataSourceFactory<IPokemon> {
        return ListingsPagingDataSourceFactory(useCase, errorState, uiScope, application);
    }

    @Provides
    @MainListUIScope
    fun provideMainListViewModel(application: MainApplication,
                                 dataSourceFactory: ListingsPagingDataSourceFactory<IPokemon>,
                                 viewModelJob: Job,
                                 errorState: MutableLiveData<Response>): MainListViewModel {
        return MainListViewModel(application, dataSourceFactory, viewModelJob, errorState)
    }

    @Provides
    @MainListUIScope
    fun provideViewModelFactory(mainListViewModel: MainListViewModel): ViewModelFactory {
        return ViewModelFactory(mainListViewModel)
    }

}