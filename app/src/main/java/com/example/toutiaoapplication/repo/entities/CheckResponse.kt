package com.example.toutiaoapplication.repo.entities
import com.google.gson.annotations.SerializedName


data class CheckResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: Check
)

data class Check(
    @SerializedName("aid")
    val aid: Int,
    @SerializedName("uid")
    val uid: Int,
    @SerializedName("uname")
    val uname: String
)