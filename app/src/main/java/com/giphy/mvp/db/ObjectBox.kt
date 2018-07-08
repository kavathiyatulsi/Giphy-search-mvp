package com.giphy.mvp.db

import android.content.Context
import io.objectbox.BoxStore

/**
 * Singleton to keep BoxStore reference.
 */
object ObjectBox {

    lateinit var boxStore: BoxStore

    fun build(context: Context) {
        boxStore = MyObjectBox.builder().androidContext(context.applicationContext).build()
    }
}