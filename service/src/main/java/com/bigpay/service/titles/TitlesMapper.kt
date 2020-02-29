package com.bigpay.service.titles

import com.bigpay.data.tiles.TilesEntity

internal fun TitlesResponse.mapToEntity(): TilesEntity {
    return TilesEntity(id, label, priority)
}