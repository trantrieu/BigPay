package com.bigpay

import android.content.Context
import com.bigpay.network.NetworkObjectGraph
import com.bigpay.selection.SelectionProvider
import com.bigpay.selection.SelectionProviderObjectGraph
import com.bigpay.service.ServiceObjectGraph
import com.bigpay.service.ServiceProviderInterface
import com.bigpay.tiles.TilesProvider
import com.bigpay.tiles.TilesProviderObjectGraph
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class BigPayAppModule {

    @Singleton
    @Provides
    fun provideRetrofit(context: Context): Retrofit {
        return NetworkObjectGraph(context).provideRetrofit()
    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): ServiceProviderInterface {
        return ServiceObjectGraph(retrofit).provideServiceProvider()
    }

    @Singleton
    @Provides
    fun getTitleProvider(serviceProviderInterface: ServiceProviderInterface): TilesProvider {
        return TilesProviderObjectGraph(serviceProviderInterface).getTitleProviders()
    }

    @Singleton
    @Provides
    fun getSelectionProvider(serviceProviderInterface: ServiceProviderInterface): SelectionProvider {
        return SelectionProviderObjectGraph(serviceProviderInterface).getSelectionProviders()
    }

    @Provides
    fun provideCompositeDisposal(): CompositeDisposable {
        return CompositeDisposable()
    }
}
