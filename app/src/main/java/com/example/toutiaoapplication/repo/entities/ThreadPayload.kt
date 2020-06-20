package com.example.toutiaoapplication.repo.entities
import com.google.gson.annotations.SerializedName


data class ThreadPayload(
    @SerializedName("fid")
    val fid: Int,
    @SerializedName("tcont")
    val tcont: String,
    @SerializedName("tname")
    val tname: String,
    @SerializedName("uid")
    val uid: Int
)