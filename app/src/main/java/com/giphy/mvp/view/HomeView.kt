package com.giphy.mvp.view

import com.giphy.mvp.model.DataItem

interface HomeView {
    fun onSearchResponse(list: List<DataItem>)
    fun showProgressBar()
    fun hideProgressBar()
    fun noResult()
    fun onApiError()
}