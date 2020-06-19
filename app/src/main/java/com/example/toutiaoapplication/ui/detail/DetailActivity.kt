package com.example.toutiaoapplication.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.toutiaoapplication.R
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_scrolling.*


class DetailActivity : AppCompatActivity(), DetailContract.View {

    override lateinit var presenter: DetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val title = intent.getStringExtra("title")!!
        val content = intent.getStringExtra("content")!!
        val time = intent.getStringExtra("time")!!
        val tid: Int = intent.getIntExtra("tid", -1)


        setSupportActionBar(findViewById(R.id.toolbar))
        toolbar_layout.title = title
        detail_content.text = content

        toolbar.setNavigationIcon(R.drawable.ic_menu_camera)
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.comment -> {
                    val intent = Intent(this, CommentsActivity::class.java).apply {
                        putExtra("tid", tid)
                    }
                    startActivity(intent)
                }
            }
            false
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