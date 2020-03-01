package com.bigpay.main

import com.bigpay.selection.SelectionProvider
import com.bigpay.selection.SelectionResult
import com.bigpay.tiles.TilesProvider
import com.bigpay.tiles.TilesResult
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

internal class MainPresenter @Inject constructor(
    private val selectionProvider: SelectionProvider,
    private val tilesProvider: TilesProvider,
    private val compositeDisposable: CompositeDisposable
) : MainContract.Presenter {

    private lateinit var view: MainContract.View

    override fun setView(view: MainContract.View) {
        this.view = view
    }

    override fun fetchTitles() {
        val disposable = tilesProvider.getTiles()
            .doOnSubscribe {
                view.showLoadingDialog()
            }.doFinally {
                view.hideLoadingDialog()
            }.subscribe({
                val success = it as? TilesResult.Success
                success?.run {
                    view.showTitles(list)
                }
            }, {
                view.onLoadTilesError(it.message ?: "Exception")
            })
        compositeDisposable.add(disposable)
    }

    override fun selection(idString: String) {
        val disposable = selectionProvider.postSelection(idString)
            .doOnSubscribe {
                view.showLoadingDialog()
            }.doFinally {
                view.hideLoadingDialog()
            }.subscribe({
                val success = it as? SelectionResult.Success
                success?.run {
                    view.showMessagePopup(message)
                }
            }, {
                view.showMessagePopup(it.message ?: "Exception")
            })
        compositeDisposable.add(disposable)
    }

    override fun destroy() {
        compositeDisposable.clear()
    }
}