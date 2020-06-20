package com.example.toutiaoapplication.repo.entities

import com.google.gson.annotations.SerializedName


data class News (
    @SerializedName("tid")   var tid: Int,       // 新闻ID
    @SerializedName("tname") var title: String,   // 新闻标题
    @SerializedName("tcont") var content: String,// 新闻内容
    @SerializedName("ttime") var time: Long,   // datetime
    @SerializedName("ttop")  var top: Int,       // 新闻置顶
    @SerializedName("tdel")  var del: Int,       // 删除标记
    @SerializedName("fid")   var fid: Int,       // 分区ID
    @SerializedName("uid")   var uid: Int        // 用户ID
)
data class ResponseNews(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: NewsData
)

data class NewsData(
    @SerializedName("endRow")
    val endRow: Int,
    @SerializedName("hasNextPage")
    val hasNextPage: Boolean,
    @SerializedName("hasPreviousPage")
    val hasPreviousPage: Boolean,
    @SerializedName("isFirstPage")
    val isFirstPage: Boolean,
    @SerializedName("isLastPage")
    val isLastPage: Boolean,
    @SerializedName("list")
    val list: List<News>,
    @SerializedName("navigateFirstPage")
    val navigateFirstPage: Int,
    @SerializedName("navigateLastPage")
    val navigateLastPage: Int,
    @SerializedName("navigatePages")
    val navigatePages: Int,
    @SerializedName("navigatepageNums")
    val navigatepageNums: List<Int>,
    @SerializedName("nextPage")
    val nextPage: Int,
    @SerializedName("pageNum")
    val pageNum: Int,
    @SerializedName("pageSize")
    val pageSize: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("prePage")
    val prePage: Int,
    @SerializedName("size")
    val size: Int,
    @SerializedName("startRow")
    val startRow: Int,
    @SerializedName("total")
    val total: Int
)
