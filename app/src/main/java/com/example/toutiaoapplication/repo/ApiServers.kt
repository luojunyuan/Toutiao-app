package com.example.toutiaoapplication.repo

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServers {

    // 返回api接口
    fun getApiService(): Apis {

        val gson: Gson = GsonBuilder().setLenient().create()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit.create(Apis::class.java)
    }

    companion object {
//        const val URL = "https://api.apiopen.top/"
        const val URL = "http://192.168.0.105:8000/"
    }
}
