package com.example.toutiaoapplication.ui.detail

import android.os.Bundle
import android.util.Log
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.utils.EXTRA_MESSAGE
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
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title
        detail_content.text = content

        // none use fab
//        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
    }

    companion object {
        const val TAG = "DetailActivity"
    }
}