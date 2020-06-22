package com.example.toutiaoapplication.ui.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.ui.comment.CommentsActivity
import com.example.toutiaoapplication.utils.toast
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_scrolling.*


class DetailActivity : AppCompatActivity(), DetailContract.View {

    override lateinit var presenter: DetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // 小心comment返回时若页面被结束他不会传递下值
        val title = intent.getStringExtra("title")!!
        val content = intent.getStringExtra("content")!!
        // val time = intent.getLongExtra("time", -1) // 不用了
        val tid: Int = intent.getIntExtra("tid", -1)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar_layout.title = title
        detail_content.text = content

        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 左侧添加一个默认的返回图标
        supportActionBar?.setHomeButtonEnabled(true) // 设置返回键可用
        toolbar.setNavigationOnClickListener {
            Log.d(TAG, "back to previous")
            finish()
        }
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.comment -> {
                    val intent = Intent(this, CommentsActivity::class.java).apply {
                        putExtra("tid", tid.toString())
                    }
                    startActivity(intent)
                }
                R.id.good -> toast("给主题点赞👍！")
                R.id.bad -> toast("给主题点踩👎！")
                R.id.star -> toast("收藏主题")
            }
            true
        }
        // none use fab
//        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail,menu)
        return super.onCreateOptionsMenu(menu)
    }

    companion object {
        const val TAG = "DetailActivity"
    }
}