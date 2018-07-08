package com.giphy.mvp.di.module


import com.giphy.mvp.api.Endpoints
import com.giphy.mvp.di.scope.ActivityScope
import com.giphy.mvp.presenter.ExoVideoPlayerPresenter
import com.giphy.mvp.presenter.HomePresenter
import com.giphy.mvp.providers.AppSchedulerProvider
import dagger.Module
import dagger.Provides
import io.objectbox.BoxStore
import io.reactivex.disposables.CompositeDisposable

@Module
class HomeActivityModule {

    @Provides
    @ActivityScope
    internal fun providesHomePresenter(api: Endpoints, disposable: CompositeDisposable, scheduler: AppSchedulerProvider): HomePresenter =
            HomePresenter(api, disposable, scheduler)

    @Provides
    @ActivityScope
    internal fun providesExoVideoPlayerPresenter(boxStore: BoxStore): ExoVideoPlayerPresenter =
            ExoVideoPlayerPresenter(boxStore)
}
