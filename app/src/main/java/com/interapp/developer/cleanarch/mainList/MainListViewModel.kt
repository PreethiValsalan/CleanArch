package com.interapp.developer.cleanarch.mainList

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.interapp.developer.cleanarch.paging.ListingsPagingDataSourceFactory
import com.interapp.developer.domain.model.IPokemon
import com.interapp.developer.domain.model.Response
import kotlinx.coroutines.Job
import java.util.concurrent.Executors

val PAGE_SIZE = 4

/**
 * ViewModel for the main activity
Created by Preethi Valsalan on 2019-10-26
 */
class MainListViewModel(application: Application,
                              val pagingFactory: ListingsPagingDataSourceFactory<IPokemon>,
                              val viewModelJob: Job,
                              val responseState : MutableLiveData<Response>) : AndroidViewModel(application) {

    val closingListLiveData : LiveData<PagedList<IPokemon>> by lazy {
        val executor =  Executors.newSingleThreadExecutor()

        val pageListConfig = PagedList.Config.Builder()
                .setPageSize(PAGE_SIZE)
                .setPrefetchDistance(PAGE_SIZE *2)
                .setEnablePlaceholders(false)
                .build();

         LivePagedListBuilder(pagingFactory, pageListConfig).setFetchExecutor(executor).build()


    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


    fun invalidateDataSource() =
            pagingFactory.sourceLiveData.value?.invalidate()
}
