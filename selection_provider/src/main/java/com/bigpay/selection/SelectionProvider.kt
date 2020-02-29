package com.bigpay.selection

import com.bigpay.service.ServiceProviderInterface
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SelectionProvider @Inject constructor(private val serviceProvider: ServiceProviderInterface) {

    fun postSelection(idString: String): Single<SelectionResult> {
        return serviceProvider.selection(idString).map {
            SelectionResult.Success(it.message) as SelectionResult
        }.observeOn(AndroidSchedulers.mainThread())
    }

}