package com.example.toutiaoapplication.ui.thread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.ui.detail.DetailActivity
import com.example.toutiaoapplication.utils.ActivityUtils
import kotlinx.android.synthetic.main.activity_article.*

class ArticleActivity : AppCompatActivity() {
    var title: String? = null
    var content: String? = null
    private var time: Long? = null
    var tid: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        setSupportActionBar(toolbar)
        // 跳转到该activity之前必须要先放入这些ExtraData
        receiveExtra()

        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 左侧添加一个默认的返回图标
        supportActionBar?.setHomeButtonEnabled(true) // 设置返回键可用
        toolbar.setNavigationOnClickListener {
            finish()
        }

        val contentFragment = ArticleContentFragment.newInstance(content!!, time!!)
        val commentFragment = ArticleCommentFragment.newInstance(tid!!)
        ActivityUtils.addFragmentToActivity(supportFragmentManager, contentFragment, R.id.article_content_view)
        ActivityUtils.addFragmentToActivity(supportFragmentManager, commentFragment, R.id.comment_content_view)
    }

    private fun receiveExtra() {
        title = intent.getStringExtra("title")!!
        content = intent.getStringExtra("content")!!
        time = intent.getLongExtra("time", -1)
        tid = intent.getIntExtra("tid", -1)

        toolbar_layout.title = title
    }
}