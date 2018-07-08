package com.giphy.mvp.app

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.giphy.mvp.db.ObjectBox
import com.giphy.mvp.di.component.AppComponent
import com.giphy.mvp.di.component.DaggerAppComponent
import com.giphy.mvp.di.module.AppModule

class GiphyApp : Application() {

    companion object {
        @JvmStatic
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
        ObjectBox.build(this)
        Fresco.initialize(this)
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}