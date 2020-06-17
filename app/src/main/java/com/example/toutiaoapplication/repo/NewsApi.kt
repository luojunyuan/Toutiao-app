package com.example.toutiaoapplication.repo

import com.example.toutiaoapplication.repo.entities.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApi {

    @GET("api/forum")
    fun listNews(): Call<List<News?>?>?
}