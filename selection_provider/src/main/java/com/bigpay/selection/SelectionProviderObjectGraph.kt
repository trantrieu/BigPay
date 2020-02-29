package com.bigpay.selection

import com.bigpay.service.ServiceProviderInterface

class SelectionProviderObjectGraph(serviceProviderInterface: ServiceProviderInterface) {

    private val component = DaggerSelectionProviderComponent.builder()
        .bindsServiceProvider(serviceProviderInterface)
        .build()

    fun getSelectionProviders() : SelectionProvider {
        return component.provideSelectionProvider()
    }
}