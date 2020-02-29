package com.bigpay.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.bigpay.BigPayApp
import com.bigpay.R
import com.bigpay.base.BaseActivity
import com.bigpay.data.tiles.TilesEntity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View, TitleViewHolder.OnViewHolderClickListener {

    @Inject
    lateinit var presenter: MainContract.Presenter

    private val adapter = TitleAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val app = application as BigPayApp
        DaggerMainAppComponent.builder()
            .bigPayAppComponent(app.getBigPayAppComponent())
            .build()
            .inject(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        swipeRefreshLayout.setOnRefreshListener {
            presenter.fetchTitles()
        }

        presenter.setView(this)
        presenter.fetchTitles()
    }

    override fun onLoadTilesError(message: String) {
        swipeRefreshLayout.isRefreshing = false
        showPopup(message)
    }

    override fun showTitles(list: List<TilesEntity>) {
        swipeRefreshLayout.isRefreshing = false
        adapter.setNewList(list)
    }

    override fun showMessagePopup(message: String) {
        showPopup(message)
    }

    override fun showLoadingDialog() {
        showLoadingDialogSpinner()
    }

    override fun hideLoadingDialog() {
        hideLoadingDialogSpinner()
    }

    override fun onViewHolderClick(titleEntity: TilesEntity) {
        presenter.selection(titleEntity.id)
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }
}
