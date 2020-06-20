package com.example.toutiaoapplication.repo.entities
import com.google.gson.annotations.SerializedName

// This is none use
data class logoutResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: NoneUse
)

class NoneUse(
)