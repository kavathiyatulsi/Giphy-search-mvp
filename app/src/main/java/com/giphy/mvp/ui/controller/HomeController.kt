package com.giphy.mvp.ui.controller

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.bluelinelabs.conductor.RouterTransaction
import com.giphy.mvp.R
import com.giphy.mvp.di.component.DaggerHomeActivityComponent
import com.giphy.mvp.di.module.HomeActivityModule
import com.giphy.mvp.interfaces.OnItemClickListener
import com.giphy.mvp.model.DataItem
import com.giphy.mvp.presenter.HomePresenter
import com.giphy.mvp.ui.adapter.HomeAdapter
import com.giphy.mvp.view.HomeView
import com.mancj.materialsearchbar.MaterialSearchBar
import kotlinx.android.synthetic.main.empty_view.view.*
import kotlinx.android.synthetic.main.home_controller.view.*
import javax.inject.Inject


class HomeController(args: Bundle?) : BaseController(args), HomeView, OnItemClickListener, MaterialSearchBar.OnSearchActionListener {

    @Inject
    lateinit var presenter: HomePresenter
    private lateinit var controllerView: View
    private val keySearchQuery = "searchQuery"

    companion object {
        fun getInstance(): HomeController {
            val args = Bundle()
            return HomeController(args)
        }
    }

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.home_controller, container, false)
    }

    override fun onActivityInject() {
        DaggerHomeActivityComponent.builder().appComponent(getAppComponent())
                .homeActivityModule(HomeActivityModule())
                .build()
                .inject(this)
    }

    override fun onViewBound(view: View) {
        controllerView = view
        presenter.attachView(this@HomeController)
        view.searchBar.setOnSearchActionListener(this@HomeController)
        view.searchBar.hideSuggestionsList()
        manageEmptyState(true)
    }

    override fun onSaveViewState(view: View, outState: Bundle) {
        super.onSaveViewState(view, outState)
        outState.putString(keySearchQuery, view.searchBar.text)
    }

    override fun onRestoreViewState(view: View, savedViewState: Bundle) {
        super.onRestoreViewState(view, savedViewState)
        val searchQuery = savedViewState.getString(keySearchQuery)
        presenter.searchGiphyVideo(searchQuery.toString())
    }

    override fun onSearchResponse(list: List<DataItem>) {
        manageEmptyState(false)
        controllerView.recyclerView.apply {
            this?.layoutManager = GridLayoutManager(applicationContext, 3)
            this?.adapter = HomeAdapter(list, this@HomeController)
        }
    }

    override fun onItemClick(dataItem: DataItem) {
        router.pushController(RouterTransaction.with(ExoVideoPlayerController.getInstance(dataItem.id, dataItem.images.original.mp4)))
    }

    override fun onButtonClicked(buttonCode: Int) {

    }

    override fun onSearchStateChanged(enabled: Boolean) {
        if (!enabled)
            manageEmptyState(true)
    }

    override fun onSearchConfirmed(queryString: CharSequence?) {
        presenter.searchGiphyVideo(queryString.toString())
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(controllerView.searchBar?.windowToken, 0)
    }

    override fun showProgressBar() {
        showProgress()
    }

    override fun hideProgressBar() {
        cancelProgress()
    }

    override fun noResult() {
        controllerView.tvEmpty.text = activity?.getString(R.string.no_data_found)
        manageEmptyState(true)
    }

    override fun onApiError() {
        controllerView.tvEmpty.text = activity?.getString(R.string.some_thing_wrong)
        manageEmptyState(true)
    }

    private fun manageEmptyState(isNoDataFound: Boolean) {
        if (isNoDataFound) {
            controllerView.recyclerView?.visibility = View.GONE
            controllerView.emptyView?.visibility = View.VISIBLE
        } else {
            controllerView.recyclerView?.visibility = View.VISIBLE
            controllerView.emptyView?.visibility = View.GONE
        }
    }
}