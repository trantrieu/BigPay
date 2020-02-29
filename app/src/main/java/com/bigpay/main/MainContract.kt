package com.bigpay.main

import com.bigpay.data.tiles.TilesEntity

interface MainContract {

    interface View {

        fun showTitles(list: List<TilesEntity>)

        fun showMessagePopup(message: String)

        fun onLoadTilesError(message: String)

        fun showLoadingDialog()

        fun hideLoadingDialog()
    }

    interface Presenter {

        fun setView(view: View)

        fun fetchTitles()

        fun selection(idString: String)

        fun destroy()
    }

}