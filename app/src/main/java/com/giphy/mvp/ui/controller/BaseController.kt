package com.giphy.mvp.ui.controller

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.giphy.mvp.app.GiphyApp
import com.giphy.mvp.di.component.AppComponent
import com.giphy.mvp.ui.dialog.CustomProgressDialog

abstract class BaseController(args: Bundle?) : Controller(args) {

    protected abstract fun inflateView(inflater: LayoutInflater, container: ViewGroup): View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflateView(inflater, container)
        onActivityInject()
        return view
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        onViewBound(view)
    }

    private var progressDialog: CustomProgressDialog? = null

    fun showProgress() {
        if (progressDialog == null) {
            progressDialog = CustomProgressDialog(activity as Context, null)
        }

        progressDialog?.show()
    }

    fun cancelProgress() {
        progressDialog?.cancel()
    }


    fun getAppComponent(): AppComponent = GiphyApp.appComponent

    protected abstract fun onActivityInject()

    abstract fun onViewBound(view: View)

}