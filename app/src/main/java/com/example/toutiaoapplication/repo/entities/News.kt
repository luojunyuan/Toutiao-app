package com.example.toutiaoapplication.repo.entities

data class News (
    var tid: Int,       // 新闻ID
    var name: String,   // 新闻标题
    var content: String,// 新闻内容
    var time: String,   // datetime
    var top: Int,       // 新闻置顶
    var del: Int,       // 删除标记
    var fid: Int,       // 分区ID
    var uid: Int        // 用户ID
)
