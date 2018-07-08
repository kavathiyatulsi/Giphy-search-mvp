package com.giphy.mvp.di.component

import android.app.Application
import android.content.res.Resources
import com.giphy.mvp.api.Endpoints
import com.giphy.mvp.di.module.ApiModule
import com.giphy.mvp.di.module.AppModule
import com.giphy.mvp.di.module.OkHttpModule
import com.giphy.mvp.di.module.RetrofitModule
import com.giphy.mvp.providers.AppSchedulerProvider
import com.google.gson.Gson
import dagger.Component
import io.objectbox.BoxStore
import io.reactivex.disposables.CompositeDisposable
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (RetrofitModule::class), (ApiModule::class), (OkHttpModule::class)])
interface AppComponent {
    fun application(): Application
    fun gson(): Gson
    fun resources(): Resources
    fun retrofit(): Retrofit
    fun endpoints(): Endpoints
    fun cache(): Cache
    fun client(): OkHttpClient
    fun loggingInterceptor(): HttpLoggingInterceptor
    fun compositeDisposable(): CompositeDisposable
    fun schedulerProvider(): AppSchedulerProvider
    fun boxProvider(): BoxStore
}