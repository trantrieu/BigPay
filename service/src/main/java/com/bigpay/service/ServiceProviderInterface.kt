package com.bigpay.service

import com.bigpay.data.selections.SelectionEntity
import com.bigpay.data.tiles.TilesEntity
import io.reactivex.Single

interface ServiceProviderInterface {

    fun getTitles(): Single<List<TilesEntity>>

    fun selection(idString: String): Single<SelectionEntity>

}