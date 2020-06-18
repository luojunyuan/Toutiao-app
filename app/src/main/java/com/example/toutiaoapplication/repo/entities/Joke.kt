package com.example.toutiaoapplication.repo.entities

import com.google.gson.annotations.SerializedName

data class Joke (

    @SerializedName("code") val code : Int,
    @SerializedName("message") val message : String,
    @SerializedName("result") val result : String
)