package com.giphy.mvp.presenter

import com.giphy.mvp.BuildConfig
import com.giphy.mvp.api.Endpoints
import com.giphy.mvp.providers.SchedulerProvider
import com.giphy.mvp.view.HomeView
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomePresenter @Inject constructor(val api: Endpoints, val disposable: CompositeDisposable, val scheduler: SchedulerProvider) {

    private var homeView: HomeView? = null

    fun attachView(view: HomeView?) {
        homeView = view
    }

    fun searchGiphyVideo(searchKey: String) {
        homeView?.showProgressBar()
        disposable.add(api.search(BuildConfig.GIPHY_API_TOKEN, searchKey)
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribe(
                        { result ->
                            homeView?.hideProgressBar()

                            if (result.data == null || result.data.isEmpty()) {
                                homeView?.noResult()
                            } else {
                                homeView?.onSearchResponse(result?.data)
                            }
                        },
                        { _ ->
                            homeView?.hideProgressBar()
                            homeView?.onApiError()
                        })
        )
    }

}