package com.example.toutiaoapplication.repo.entities.payload
import com.google.gson.annotations.SerializedName


data class InfoPayload(
    @SerializedName("udesc")
    val udesc: String,
    @SerializedName("umail")
    val umail: String
)