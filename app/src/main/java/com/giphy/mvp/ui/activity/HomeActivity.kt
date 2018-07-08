package com.giphy.mvp.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.giphy.mvp.R
import com.giphy.mvp.ui.controller.HomeController
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

    private lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        router = Conductor.attachRouter(this, controllerContainer, savedInstanceState)
        if (!router.hasRootController()) {
            router.pushController(RouterTransaction.with(HomeController.getInstance()))
        }
    }

    override fun onBackPressed() {
        if (!router.popToRoot()) {
            super.onBackPressed()
        }
    }
}
