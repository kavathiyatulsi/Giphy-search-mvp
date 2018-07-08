package com.giphy.mvp.interfaces

import com.giphy.mvp.model.DataItem

interface OnItemClickListener {
    fun onItemClick(dataItem: DataItem)
}