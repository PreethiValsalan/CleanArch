package com.interapp.developer.cleanarch

import com.interapp.developer.datawrapper.dataSource.DaggerDataSourceComponent
import com.interapp.developer.datawrapper.dataSource.DataSourceComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
Created by Preethi Valsalan on 2019-10-26
 */
class MainApplication : DaggerApplication() {

    private lateinit var dataSourceComponent: DataSourceComponent

    override fun onCreate() {
        dataSourceComponent = DaggerDataSourceComponent.builder().dataSourceModule(ENDPOINT_URL).build();
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().usecaseComponent(dataSourceComponent).application(this@MainApplication).build();
    }
}


