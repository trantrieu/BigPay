package com.bigpay

import android.content.Context
import com.bigpay.selection.SelectionProvider
import com.bigpay.tiles.TilesProvider
import dagger.BindsInstance
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [BigPayAppModule::class])
interface BigPayAppComponent {

    fun exposeSelectionProvider(): SelectionProvider

    fun exposeTitlesProvider(): TilesProvider

    fun exposeCompositeDisposal(): CompositeDisposable

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindsContext(context: Context): Builder

        fun build(): BigPayAppComponent

    }
}