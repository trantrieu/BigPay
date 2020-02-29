package com.bigpay.service.titles

import com.google.gson.annotations.SerializedName

internal data class TitlesResponse(
    @SerializedName("Id")
    val id: String,
    @SerializedName("Label")
    val label: String,
    @SerializedName("Priority")
    val priority: Float)