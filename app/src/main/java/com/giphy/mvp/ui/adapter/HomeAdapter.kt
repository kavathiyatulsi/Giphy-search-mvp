package com.giphy.mvp.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.giphy.mvp.R
import com.giphy.mvp.interfaces.OnItemClickListener
import com.giphy.mvp.model.DataItem
import kotlinx.android.synthetic.main.row_home.view.*

class HomeAdapter(private var listOfVideos: List<DataItem>, private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_home, parent, false))

    override fun getItemCount(): Int = listOfVideos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindItems(listOfVideos[position], onItemClickListener)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(dataItem: DataItem, onItemClickListener: OnItemClickListener) {
            itemView.ivThumb?.setImageURI(dataItem.images.original.webp)
            itemView.ivThumb?.setOnClickListener {
                onItemClickListener.onItemClick(dataItem)
            }
        }
    }

}