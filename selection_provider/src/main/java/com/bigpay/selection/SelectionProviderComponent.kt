package com.bigpay.selection

import com.bigpay.service.ServiceProviderInterface
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
internal interface SelectionProviderComponent {

    fun provideSelectionProvider(): SelectionProvider

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindsServiceProvider(serviceProviderInterface: ServiceProviderInterface): Builder

        fun build(): SelectionProviderComponent

    }
}