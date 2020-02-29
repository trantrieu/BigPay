package com.bigpay.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.bigpay.R
import com.bigpay.data.tiles.TilesEntity

internal class TitleViewHolder(
    view: View,
    private val onViewHolderClickListener: OnViewHolderClickListener
) : RecyclerView.ViewHolder(view) {

    private val tilesTextView = view.findViewById<TextView>(R.id.tiles_text_view)
    private lateinit var titleEntity: TilesEntity

    init {
        view.setOnClickListener {
            onViewHolderClickListener.onViewHolderClick(titleEntity)
        }
    }

    fun bind(titleEntity: TilesEntity) {
        this.titleEntity = titleEntity
        tilesTextView.text = titleEntity.label
    }

    internal interface OnViewHolderClickListener {

        fun onViewHolderClick(titleEntity: TilesEntity)

    }
}