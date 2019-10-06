package com.ivin.examples.mvp.utils

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import com.ivin.examples.mvp.R

/**
 * <P>Created by ivin on 2019/3/9.</P>
 */
class LoadingDialog(context: Context) : AppCompatDialog(context, R.style.DialogStyle) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = View.inflate(context, R.layout.dialog_loading, null)
        setContentView(view)
        window?.setGravity(Gravity.CENTER)
    }

    override fun show() {
        if (context is AppCompatActivity) {
            if ((context as AppCompatActivity).isFinishing) {
                return
            }
        }
        super.show()
    }
}