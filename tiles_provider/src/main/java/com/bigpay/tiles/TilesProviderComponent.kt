package com.bigpay.tiles

import com.bigpay.service.ServiceProviderInterface
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
internal interface TilesProviderComponent {

    fun provideTitlesProvider(): TilesProvider

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindsServiceProvider(serviceProviderInterface: ServiceProviderInterface): Builder

        fun build(): TilesProviderComponent

    }
}