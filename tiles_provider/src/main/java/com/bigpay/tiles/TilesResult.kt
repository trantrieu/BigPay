package com.bigpay.tiles

import com.bigpay.data.tiles.TilesEntity

sealed class TilesResult {

    data class Success(val list: List<TilesEntity>): TilesResult()
}