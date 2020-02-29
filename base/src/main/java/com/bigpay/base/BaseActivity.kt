package com.bigpay.base

import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    private var dialog: AlertDialog? = null
    private var dialogSpinner: AlertDialog? = null

    protected fun showPopup(message: String) {
        dialog?.dismiss()
        dialog = AlertDialog.Builder(this)
            .setMessage(message)
            .setTitle(R.string.popup_title)
            .setPositiveButton(R.string.ok) { dialog, _ -> dialog.dismiss() }
            .setCancelable(false)
            .create()
        dialog?.show()
    }

    protected fun showLoadingDialogSpinner() {
        dialogSpinner?.dismiss()
        dialogSpinner = AlertDialog.Builder(this)
            .setView(R.layout.dialog_progress)
            .setTitle(R.string.loading)
            .setCancelable(false)
            .create()
        dialogSpinner?.show()
    }

    protected fun hideLoadingDialogSpinner() {
        dialogSpinner?.dismiss()
    }
}