package com.giphy.mvp.di.component


import com.giphy.mvp.di.module.HomeActivityModule
import com.giphy.mvp.di.scope.ActivityScope
import com.giphy.mvp.ui.activity.HomeActivity
import com.giphy.mvp.ui.controller.ExoVideoPlayerController
import com.giphy.mvp.ui.controller.HomeController
import dagger.Component

@ActivityScope
@Component(dependencies = [(AppComponent::class)], modules = [(HomeActivityModule::class)])
interface HomeActivityComponent {

    fun inject(homeActivity: HomeActivity)
    fun inject(homeController: HomeController)
    fun inject(exoVideoPlayerController: ExoVideoPlayerController)
}
