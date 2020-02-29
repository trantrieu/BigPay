package com.bigpay.network

import android.content.Context
import retrofit2.Retrofit

class NetworkObjectGraph(context: Context) {

    private var component = DaggerNetworkComponent
        .builder()
        .bindsContext(context)
        .build()

    fun provideRetrofit() : Retrofit {
        return component.provideRetrofit2()
    }

}