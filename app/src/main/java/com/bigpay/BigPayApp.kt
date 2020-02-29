package com.bigpay

import android.app.Application

class BigPayApp : Application() {

    private lateinit var component: BigPayAppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerBigPayAppComponent
            .builder()
            .bindsContext(this)
            .build()
    }

    fun getBigPayAppComponent() = component
}