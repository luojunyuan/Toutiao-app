package com.example.toutiaoapplication.base

import android.os.Handler
import android.os.Looper

interface BasePresenter {
    fun start()

    fun uiThread(f: () -> Unit) {
        handler.post{ f() }
    }

    companion object {
        val handler by lazy {
            Handler(Looper.getMainLooper())
        }
    }
}