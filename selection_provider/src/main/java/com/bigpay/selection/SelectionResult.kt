package com.bigpay.selection

sealed class SelectionResult {

    data class Success(val message: String) : SelectionResult()
}