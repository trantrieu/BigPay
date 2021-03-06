package com.bigpay.network

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
internal interface NetworkComponent {

    fun provideRetrofit2() : Retrofit

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindsContext(context: Context): Builder

        fun build(): NetworkComponent

    }
}