package com.example.toutiaoapplication.repo.entities

import com.google.gson.annotations.SerializedName

data class ResponseUser(
    @SerializedName("code") val ret_code: String,
    @SerializedName("data") val data: User
)

data class User(
    @SerializedName("aid") val aid : Int,
    @SerializedName("udesc") val descriptor : String,// 简介
    @SerializedName("uid") val uid : Int,
    @SerializedName("umail") val email : String,
    @SerializedName("uname") val username : String,
    @SerializedName("ustat") val ban : Int,// 0正常，1封禁
    @SerializedName("utime") val register_day : Long// 注册时间 datetime? 注意大小 1592276029000
)