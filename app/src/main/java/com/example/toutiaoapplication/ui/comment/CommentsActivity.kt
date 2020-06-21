package com.example.toutiaoapplication.ui.comment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.repo.entities.Comments
import kotlinx.android.synthetic.main.activity_comments.*

class CommentsActivity : AppCompatActivity(), CommentContract.View {
    private lateinit var commentManager: RecyclerView.LayoutManager
    private lateinit var commentAdapter: RecyclerView.Adapter<*>
    private lateinit var recyclerView: RecyclerView

    override fun setData(data: List<Comments>) {
        commentAdapter = CommentAdapter(data)
        commentAdapter.notifyDataSetChanged()
        recyclerView.adapter = commentAdapter
    }

    override lateinit var presenter: CommentContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        // 接受传来的tid，请求data
        val id = intent.getStringExtra("tid")!!
        presenter = CommentPresenter(this)
        presenter.getComments(id)

        commentManager = LinearLayoutManager(this)
        // 从api获取数据，参考 homeAdapter 配置
        recyclerView = findViewById<RecyclerView>(R.id.commentList).apply {
            setHasFixedSize(true)
            layoutManager = commentManager
        }

        addButton.setOnClickListener {

        }
    }


}