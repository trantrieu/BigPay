package com.bigpay.tiles

import com.bigpay.service.ServiceProviderInterface

class TilesProviderObjectGraph(serviceProviderInterface: ServiceProviderInterface) {

    private val component = DaggerTilesProviderComponent.builder()
        .bindsServiceProvider(serviceProviderInterface)
        .build()

    fun getTitleProviders() : TilesProvider{
        return component.provideTitlesProvider()
    }
}