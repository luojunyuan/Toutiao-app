package com.example.toutiaoapplication.repo

import com.example.toutiaoapplication.repo.entities.*
import com.example.toutiaoapplication.repo.entities.payload.*
import retrofit2.Call
import retrofit2.http.*


interface Apis {

//    @GET("api/forum")
//    fun allForum(): Call<News>

    // 指定分区获取帖子
    @GET("api/fthread/{fid}")
    fun getExactForum(@Path("fid") key: Int): Call<ResponseNews>

    // 返回所有thread
    @GET("api/thread")
    fun allNews(): Call<ResponseNews> // Response<List<News>>

    // 搜索
    @GET("/api/search/t/{title_string}")
    fun searchWord(@Path("title_string") keyWord: String): Call<ResponseNews>

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
    @GET("/api/check")
    fun checkLogin(): Call<CheckResponse>

    // 通过uid获取用户所有评论
    @GET("/api/ucomm/{uid}")
    fun getUserComment(@Path("uid") uid: Int)

    /**
     * 通过cid（一条评论id）获取一条评论详情内容
     */
    @GET("/api/comm/{cid}")
    fun getComment(@Path("cid") cid: Int)

    // session 保持
    // https://blog.csdn.net/Kedongyu_/article/details/82662795
    @Headers("Content-Type: application/json")
    @POST("api/login")
    fun loginUser(@Body payload: LoginPayload): Call<ResponseUser>

    /**
     * 添加主题
     */
    //
    @POST("/api/thread")
    fun createThread(@Body payload: ThreadPayload): Call<ResponseNews> // data 不返回数据

    /**
     * 添加评论
     * code 返回 0 发送成功
     */
    @POST("/api/comm")
    fun postComment(@Body payload: CommentPayload): Call<ResponseComments>

    // 删除评论
    // @DELETE("/api/comm/{cid}")

    // 点赞
    // "/api/rate/{tid}"
    // 点踩
    // "/api/rate/{tid}"

    // 获取分区所有主题
    // "/api/fthread/{fid}"

    // 获取置顶主题
    @GET("/api/top")
    fun getTop(): Call<ResponseSingleNew>

    // logout
    @GET("/api/logout")
    fun logout(): Call<logoutResponse>

    // 注册用户api
    @POST("/api/register")
    fun register(@Body payload: RegisterPayload): Call<ResponseUser>

    // 获取用户信息 "/api/users/{value}"// username or uid
    @GET("/api/users/{value}")
    fun getInfo(@Path("value") key: String): Call<ResponseUser>
    // 修改用户信息 "/api/users"
    @PUT("/api/users")
    fun updateInfo(@Body payload: InfoPayload): Call<ResponseUser>
    // 修改自己的密码 "/api/upass"

    // -----------------------------------------------------------
    // 返回四种权限类型（没啥用？）
    // @GET("/api/auth[/1or2or3or4]")
    // 通过 aid 修改权限信息？
    // @PUT("/api/auth/{aid}")
    // 获取分区信息? (什么玩意儿)
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