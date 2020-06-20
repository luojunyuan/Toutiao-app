package com.example.toutiaoapplication.repo.entities
import com.google.gson.annotations.SerializedName


data class RegisterPayload(
    @SerializedName("umail")
    val umail: String,
    @SerializedName("uname")
    val uname: String,
    @SerializedName("upass")
    val upass: String
)