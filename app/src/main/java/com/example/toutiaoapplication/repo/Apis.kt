package com.example.toutiaoapplication.repo

import com.example.toutiaoapplication.repo.entities.*
import retrofit2.Call
import retrofit2.http.*


interface Apis {

//    @GET("api/forum")
//    fun allForum(): Call<News>

    // 返回所有thread
    @GET("api/thread")
    fun allNews(): Call<ResponseNews> // Response<List<News>>

    /**
     * 获取主题
     */
    // /api/thread/{tid}

    // 根据tid获取评论
    @GET("/api/tcomm/{tid}")
    fun getComments(@Path("tid") tid: String): Call<ResponseComments>

    /**
     * 获取thread评级
     */
    // @GET("/api/rate/{tid}")

    // 通过uid查询用户信息
    @GET("api/users/{uid}")
    fun getUser(@Path("uid") uid: Int): Call<ResponseUser>

    /**
     * 登陆状态检查
     */
    // /api/check

    // 通过uid获取用户所有评论
    @GET("/api/ucomm/{uid}")
    fun getUserComment(@Path("uid") uid: Int)

    /**
     * 通过cid（一条评论id）获取一条评论详情内容
     */
    @GET("/api/comm/{cid}")
    fun getComment(@Path("cid") cid: Int)

    @Headers("Content-Type: application/json")
    @POST("api/login")
    fun loginUser(@Body payload: LoginPayload): Call<ResponseUser>

    /**
     * 添加主题
     */
    // "/api/thread"

    /**
     * 添加评论
     */
    // @POST("/api/comm")

    // 删除评论
    // @DELETE("/api/comm/{cid}")

    // 点赞
    // "/api/rate/{tid}"
    // 点踩
    // "/api/rate/{tid}"

    // 获取分区所有主题
    // "/api/fthread/{fid}"

    // 置顶主题
    // /api/top

    // logout
    @GET("/api/logout")
    fun logout(): Call<logoutResponse>

    // 注册用户api
    //  "/api/register"

    // 获取用户信息 "/api/users/{value}"
    // 修改用户信息 "/api/users"
    // 修改自己的密码 "/api/upass"

    // -----------------------------------------------------------
    // 返回四种权限类型（没啥用？）
    // @GET("/api/auth[/1or2or3or4]")
    // 通过 aid 修改权限信息？
    // @PUT("/api/auth/{aid}")
    // 获取分区信息?
    // @GET("/api/forum[/1]")
    // 添加分区
    // @POST("/api/forum")
    // 删除分区
    // /api/forum/{fid}
    // 修改分区信息
    // "/api/forum/{fid}"
    // 置顶/取消置顶某一主题
    // "/api/top/{tid}"

    // 修改主题
    // "/api/thread"
    // 删除
    // "/api/thread/{tid}"
    // 管理登录
    // /api/admin/login
}