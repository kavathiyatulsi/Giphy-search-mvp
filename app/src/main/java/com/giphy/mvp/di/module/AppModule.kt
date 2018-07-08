package com.giphy.mvp.di.module

import android.app.Application
import android.content.Context
import com.giphy.mvp.db.MyObjectBox
import com.giphy.mvp.providers.AppSchedulerProvider
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.objectbox.BoxStore
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun providesGson() = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create()

    @Provides
    @Singleton
    fun providesApplication() = application

    @Provides
    @Singleton
    fun providesResources() = application.resources

    @Provides
    fun provideAppContext() = application.applicationContext

    @Provides
    @Singleton
    fun provideCompositeDisposable() = CompositeDisposable()

    @Provides
    @Singleton
    fun provideSchedulerProvider() = AppSchedulerProvider()

    @Provides
    @Singleton
    fun provideObjectBox(context: Context): BoxStore {
        return MyObjectBox.builder().androidContext(context.applicationContext).build()
    }

}