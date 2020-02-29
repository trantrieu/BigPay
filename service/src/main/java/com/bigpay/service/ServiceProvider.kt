package com.bigpay.service

import com.bigpay.data.selections.SelectionEntity
import com.bigpay.data.tiles.TilesEntity
import com.bigpay.service.selection.mapToEntity
import com.bigpay.service.titles.mapToEntity
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ServiceProvider @Inject constructor(private val service: Service): ServiceProviderInterface {

    override fun getTitles(): Single<List<TilesEntity>> {
        return service.getTitles().map { list ->
            list.map { response ->
                response.mapToEntity()
            }
        }
    }

    override fun selection(idString: String): Single<SelectionEntity> {
        return service.selection(idString).map { response ->
            response.mapToEntity()
        }
    }

}