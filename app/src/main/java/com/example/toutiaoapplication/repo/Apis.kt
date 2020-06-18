package com.example.toutiaoapplication.repo

import com.example.toutiaoapplication.repo.entities.LoginPayload
import com.example.toutiaoapplication.repo.entities.News
import com.example.toutiaoapplication.repo.entities.User
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*


interface Apis {

    @GET("api/forum")
    fun listNews(): Call<News>

    @GET("api/users/{uid}")
    fun getUser(@Path("uid") uid: Int): Call<User>

    @Headers("Content-Type: application/json")
    @POST("api/login")
    fun loginUser(@Body payload: LoginPayload): Call<User>
}