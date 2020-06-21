package com.example.toutiaoapplication.repo.entities
import com.google.gson.annotations.SerializedName


data class CommentPayload(
    @SerializedName("ccont")
    val ccont: String,
    @SerializedName("tid")
    val tid: Int,
    @SerializedName("uid")
    val uid: Int
)