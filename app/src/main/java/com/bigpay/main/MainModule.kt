package com.bigpay.main

import dagger.Binds
import dagger.Module

@Module
internal abstract class MainModule {

    @Binds
    abstract fun bindsToPresenterContract(mainPresenter: MainPresenter): MainContract.Presenter

}