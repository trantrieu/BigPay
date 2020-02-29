package com.bigpay.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bigpay.R
import com.bigpay.data.tiles.TilesEntity

internal class TitleAdapter(private val onViewHolderClickListener: TitleViewHolder.OnViewHolderClickListener) :
    RecyclerView.Adapter<TitleViewHolder>() {

    private var list: List<TilesEntity> = emptyList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): TitleViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.row_title, viewGroup, false)
        return TitleViewHolder(view, onViewHolderClickListener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(viewHolder: TitleViewHolder, position: Int) {
        val titleEntity = list[position]
        viewHolder.bind(titleEntity)
    }

    fun setNewList(list: List<TilesEntity>) {
        this.list = list
        notifyDataSetChanged()
    }

}