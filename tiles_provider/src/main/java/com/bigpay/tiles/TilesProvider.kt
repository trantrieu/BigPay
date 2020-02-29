package com.bigpay.tiles

import com.bigpay.service.ServiceProviderInterface
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TilesProvider @Inject constructor(private val serviceProvider: ServiceProviderInterface) {

    fun getTiles(): Single<TilesResult> {
        return serviceProvider.getTitles()
            .map { list -> list.sortedByDescending { it.priority } }
            .map {
                TilesResult.Success(it) as TilesResult
            }.observeOn(AndroidSchedulers.mainThread())
    }

}