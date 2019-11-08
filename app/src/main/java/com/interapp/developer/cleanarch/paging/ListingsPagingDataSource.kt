package com.interapp.developer.cleanarch.paging

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import androidx.paging.PositionalDataSource
import com.interapp.developer.cleanarch.mainList.PAGE_SIZE
import com.interapp.developer.cleanarch.isNetworkAvailable
import com.interapp.developer.domain.IPokemonUsecase
import com.interapp.developer.domain.model.IPokemon
import com.interapp.developer.domain.model.IPokemonWrapper
import com.interapp.developer.domain.model.Response
import com.interapp.developer.domain.model.ResponseState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * DataSourceclass for paging
Created by Preethi Valsalan on 2019-10-26
 */
class ListingsPagingDataSource(private val usecase : IPokemonUsecase,
                               private val errorLiveData : MutableLiveData<Response>,
                               private val uiScope: CoroutineScope,
                               private val application: Application) : PageKeyedDataSource<String, IPokemon>() {

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, IPokemon>) {
        var isNetworkAvailable = isNetworkAvailable(application.applicationContext)
        if(isNetworkAvailable) {

            errorLiveData.postValue(Response.Processing(ResponseState.LOADING));
            uiScope.launch(Dispatchers.IO) { // launch a coroutine in viewModelScope
                var wrapper = usecase.getPokemonListings(0, PAGE_SIZE, isNetworkAvailable)
                callback.onResult(wrapper.pokemonList, wrapper.previous, wrapper.next)
            }
        }
        else errorLiveData.postValue(Response.Failure("Network not available", ResponseState.FAILED))

    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, IPokemon>) {
        var isNetworkAvailable = isNetworkAvailable(application.applicationContext)
        if(isNetworkAvailable) {

            errorLiveData.postValue(Response.Processing(ResponseState.LOADING));
            uiScope.launch(Dispatchers.IO) {// launch a coroutine in viewModelScope
                var wrapper = usecase.getPokemonListings(params.key, isNetworkAvailable)
                callback.onResult(wrapper.pokemonList, wrapper.next)
            }
        }
        else errorLiveData.postValue(Response.Failure("Network not available", ResponseState.FAILED))
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, IPokemon>) {
        var isNetworkAvailable = isNetworkAvailable(application.applicationContext)
        if(isNetworkAvailable) {

            errorLiveData.postValue(Response.Processing(ResponseState.LOADING));
            uiScope.launch(Dispatchers.IO) {// launch a coroutine in viewModelScope
                var wrapper = usecase.getPokemonListings(params.key, isNetworkAvailable)
                callback.onResult(wrapper.pokemonList, wrapper.previous)
            }
        }
        else errorLiveData.postValue(Response.Failure("Network not available", ResponseState.FAILED))
    }

}

/**
 * Factory class for PagingSource
 */
class ListingsPagingDataSourceFactory<T>(private val usecase : IPokemonUsecase,
                                         private val responseLiveData: MutableLiveData<Response>,
                                         private val uiScope: CoroutineScope,
                                         private val application: Application) : DataSource.Factory<String, T>() {

    val sourceLiveData = MutableLiveData<ListingsPagingDataSource>()
        get() = field

    override fun create(): DataSource<String, T> {
        var dataSource =  ListingsPagingDataSource(usecase, responseLiveData, uiScope, application)
        sourceLiveData.postValue(dataSource)
        return dataSource as DataSource<String, T>
    }


}
