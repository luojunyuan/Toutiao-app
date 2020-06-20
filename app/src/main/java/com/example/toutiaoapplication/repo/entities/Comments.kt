package com.example.toutiaoapplication.repo.entities

data class ResponseComments(
    val code: Int,
    val `data`: List<Comments>
)

data class Comments(
    val ccont: String, // 内容
    val cdel: Int,     // 删除标志
    val cid: Int,      // id
    val ctime: Long,   // 日期
    val tid: Int,      // 文章 id
    val uid: Int       // 用户 id
)