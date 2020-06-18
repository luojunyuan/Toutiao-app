package com.example.toutiaoapplication.repo.entities

import com.google.gson.annotations.SerializedName

data class ResponseNews(
    val code: Int,
    val `data`: List<News>
)

data class News (
    @SerializedName("tid")   var tid: Int,       // 新闻ID
    @SerializedName("tname") var title: String,   // 新闻标题
    @SerializedName("tcont") var content: String,// 新闻内容
    @SerializedName("ttime") var time: String,   // datetime
    @SerializedName("ttop")  var top: Int,       // 新闻置顶
    @SerializedName("tdel")  var del: Int,       // 删除标记
    @SerializedName("fid")   var fid: Int,       // 分区ID
    @SerializedName("uid")   var uid: Int        // 用户ID
)
