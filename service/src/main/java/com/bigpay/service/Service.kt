package com.bigpay.service

import com.bigpay.service.selection.SelectionResponse
import com.bigpay.service.titles.TitlesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

internal interface Service {

    @GET("/tiles")
    fun getTitles(): Single<List<TitlesResponse>>

    @POST("/selection")
    fun selection(@Query("id") idString: String): Single<SelectionResponse>
}