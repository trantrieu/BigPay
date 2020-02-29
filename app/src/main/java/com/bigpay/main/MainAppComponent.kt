package com.bigpay.main

import com.bigpay.BigPayAppComponent
import dagger.Component

@MainScope
@Component(dependencies = [BigPayAppComponent::class], modules = [MainModule::class])
interface MainAppComponent {

    fun inject(mainActivity: MainActivity)

}