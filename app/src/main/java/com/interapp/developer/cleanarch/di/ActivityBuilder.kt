package com.interapp.developer.cleanarch

import com.interapp.developer.cleanarch.mainList.MainActivity
import com.interapp.developer.cleanarch.mainList.MainListUIProvider
import com.interapp.developer.cleanarch.mainList.MainListUIScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
Created by Preethi Valsalan on 2019-10-26
 */
@Module
abstract class ActivityBuilder {

    @MainListUIScope
    @ContributesAndroidInjector(modules = [MainListUIProvider::class])
    internal abstract fun contributesScrollingActivity(): MainActivity

}