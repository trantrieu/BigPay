package com.bigpay.service

import retrofit2.Retrofit

class ServiceObjectGraph (retrofit: Retrofit){

    private var component = DaggerServiceComponent.builder()
        .bindRetrofit2(retrofit)
        .build()

    fun provideServiceProvider(): ServiceProviderInterface {
        return component.provideServiceProvider()
    }
}