package com.example.toutiaoapplication.utils

import android.os.Build
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        cookie.let {
            if (it != null) {
                builder.addHeader("Cookie", it)
                builder.addHeader("Connection", "close")
            }
            else {
                Log.d("Cookie", "Cookie not found")
            }
            return chain.proceed(builder.build())
        }
    }

    companion object {
        var cookie: String? = null
    }
}
