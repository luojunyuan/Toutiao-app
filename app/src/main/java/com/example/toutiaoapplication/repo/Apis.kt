package com.example.toutiaoapplication.repo

import com.example.toutiaoapplication.repo.entities.*
import retrofit2.Call
import retrofit2.http.*


interface Apis {

//    @GET("api/forum")
//    fun allForum(): Call<News>

    /**
     * 返回所有thread
     */
    @GET("api/thread")
    fun allNews(): Call<ResponseNews> // Response<List<News>>

    /**
     * 通过uid查询用户信息
     */
    @GET("api/users/{uid}")
    fun getUser(@Path("uid") uid: Int): Call<ResponseUser>

    @Headers("Content-Type: application/json")
    @POST("api/login")
    fun loginUser(@Body payload: LoginPayload): Call<ResponseUser>
}