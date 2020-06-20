package com.example.toutiaoapplication.repo

import com.example.toutiaoapplication.utils.AddCookiesInterceptor
import com.example.toutiaoapplication.utils.URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServers {

    // 返回api接口
    fun getApiService(): Apis {
        val client = OkHttpClient.Builder()
            .addInterceptor(AddCookiesInterceptor())
            .build()

        val gson: Gson = GsonBuilder().setLenient().create()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
        return retrofit.create(Apis::class.java)
    }
}
