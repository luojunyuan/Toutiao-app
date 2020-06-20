package com.example.toutiaoapplication.utils

import com.example.toutiaoapplication.MainActivity
import okhttp3.Interceptor
import okhttp3.Response

class AddCookiesInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val cookie = loadSavedUserInfo(MainActivity.instance.applicationContext).cookie
        builder.addHeader("Cookie", cookie)
        return chain.proceed(builder.build())
    }
}
