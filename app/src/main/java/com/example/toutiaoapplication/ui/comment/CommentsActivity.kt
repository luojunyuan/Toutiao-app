package com.example.toutiaoapplication.ui.comment

import android.app.Instrumentation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.repo.entities.Comments
import com.example.toutiaoapplication.utils.toast
import kotlinx.android.synthetic.main.activity_comments.*
import kotlinx.android.synthetic.main.dialog_comment.*

class CommentsActivity : AppCompatActivity(), CommentContract.View {
    private lateinit var commentManager: RecyclerView.LayoutManager
    private lateinit var commentAdapter: RecyclerView.Adapter<*>
    private lateinit var recyclerView: RecyclerView

    private lateinit var tid: String

    override fun setData(data: List<Comments>) {
        commentAdapter = CommentAdapter(data)
        commentAdapter.notifyDataSetChanged()
        recyclerView.adapter = commentAdapter
    }

    override lateinit var presenter: CommentContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        initData()
        initView()
    }

    private fun initData() {
        // 接受传来的tid，请求data
        tid = intent.getStringExtra("tid")!!
        presenter = CommentPresenter(this)
        presenter.getComments(tid)
    }

    private fun initView() {
        commentManager = LinearLayoutManager(this)
        // 从api获取数据，参考 homeAdapter 配置
        recyclerView = findViewById<RecyclerView>(R.id.commentList).apply {
            setHasFixedSize(true)
            layoutManager = commentManager
        }

        addButton.setOnClickListener {
            // val container = findViewById<ViewGroup>(R.id.commentList)
            val mLayoutInflater = LayoutInflater.from(this)
            val view = mLayoutInflater.inflate(R.layout.dialog_comment, null) // container

            val builder = AlertDialog.Builder(this)
            val dialog = builder
                .setTitle("评论：")
                .setView(view)
                .setNegativeButton("取消") { _, _ ->
                    toast("取消发送")
                }
                .setPositiveButton("确定") { _, _ ->
                    val comment = view.findViewById<EditText>(R.id.commentContent).text.toString()
                    // Log.d(TAG, commentContent.text.toString())
                    Log.d(TAG, comment)
                    presenter.postComment(tid, comment)
                    // Thread {
                    //     fun run () {
                    //         try {
                    //             val inst = Instrumentation()
                    //             inst.sendKeyDownUpSync(KeyEvent. KEYCODE_BACK)
                    //         } catch(e: Exception) {
                    //             e.printStackTrace()
                    //         }
                    //     }
                    // }.start()
                    finish()
                }
                .create() // 创建AlertDialog对象
            dialog.show()
        }
    }

    companion object {
        const val TAG = "CommentsActivity"
    }
}