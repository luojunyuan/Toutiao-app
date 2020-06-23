package com.example.toutiaoapplication.repo.entities
import com.google.gson.annotations.SerializedName


data class ResponseNotice(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: List<Notice>
)

data class Notice(
    @SerializedName("ncont")
    val ncont: String,
    @SerializedName("nid")
    val nid: Int,
    @SerializedName("ntime")
    val ntime: Long,
    @SerializedName("uid")
    val uid: Int
)