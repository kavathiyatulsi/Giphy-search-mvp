package com.giphy.mvp.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import com.giphy.mvp.R
import kotlinx.android.synthetic.main.dialog_progress_bar.*

class CustomProgressDialog(context: Context, color: Int?) :
        Dialog(context, android.R.style.Theme_Material_NoActionBar_TranslucentDecor) {

    private var progressBarColor: Int? = null

    init {
        progressBarColor = color
        init()
    }

    private fun init() {
        setCancelable(false)
        setContentView(R.layout.dialog_progress_bar)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progressBarColor?.let {
            progress_bar.indeterminateDrawable.setColorFilter(it, PorterDuff.Mode.SRC_IN)
        }
    }
}