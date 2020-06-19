package com.example.toutiaoapplication.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.toutiaoapplication.R

class CommentsActivity : AppCompatActivity() {
    private lateinit var commentManager: RecyclerView.LayoutManager
    private lateinit var commentAdapter: RecyclerView.Adapter<*>
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        // 接受传来的tid，请求data

        commentManager = LinearLayoutManager(this)
        // 从api获取数据，参考 homeAdapter 配置
        commentAdapter = CommentAdapter()
        recyclerView = findViewById<RecyclerView>(R.id.commentList).apply {
            setHasFixedSize(true)
            layoutManager = commentManager
            adapter = commentAdapter
        }
    }
}