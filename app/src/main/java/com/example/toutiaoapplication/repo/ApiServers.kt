package com.example.toutiaoapplication.repo

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServers {
    private val URL = "https://api.apiopen.top/"

    // 返回api接口
    fun getApiService(): NewsApi {

        val gson: Gson = GsonBuilder().setLenient().create()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(URL)
//            .client(getUnsafeOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(NewsApi::class.java)
    }
}
