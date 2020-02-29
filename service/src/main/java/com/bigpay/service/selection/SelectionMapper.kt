package com.bigpay.service.selection

import com.bigpay.data.selections.SelectionEntity

internal fun SelectionResponse.mapToEntity(): SelectionEntity {
    return SelectionEntity(message)
}