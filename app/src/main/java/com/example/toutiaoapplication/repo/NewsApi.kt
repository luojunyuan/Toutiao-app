package com.example.toutiaoapplication.repo

import com.example.toutiaoapplication.repo.entities.Joke
import com.example.toutiaoapplication.repo.entities.News
import com.example.toutiaoapplication.repo.entities.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApi {

    @GET("api/forum")
    fun listNews(): Call<News>

    @GET("xxx")
    fun loginUser(): Call<User>

    @GET("getSingleJoke")
    fun getJokeNull(): Call<Joke>
}