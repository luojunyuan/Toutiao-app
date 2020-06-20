package com.example.toutiaoapplication.repo.entities
import com.google.gson.annotations.SerializedName


data class Comments(
    val ccont: String, // 内容
    val cdel: Int,     // 删除标志
    val cid: Int,      // id
    val ctime: Long,   // 日期
    val tid: Int,      // 文章 id
    val uid: Int       // 用户 id
)

data class ResponseComments(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: Data
)

data class Data(
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
    val list: List<Comments>,
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
