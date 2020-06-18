package com.example.toutiaoapplication.repo.entities

data class ResponseComments(
    val code: Int,
    val `data`: List<Comments>
)

data class Comments(
    val ccont: String,
    val cdel: Int,
    val cid: Int,
    val ctime: Long,
    val tid: Int,
    val uid: Int
)