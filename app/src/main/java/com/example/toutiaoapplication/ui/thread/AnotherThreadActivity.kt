package com.example.toutiaoapplication.ui.thread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.toutiaoapplication.R

class AnotherThreadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_another_thread)

        // 跳转到该activity之前必须要先放入这些ExtraData
        receiveExtra()
    }

    private fun receiveExtra() {
        val title = intent.getStringExtra("title")!!
        val content = intent.getStringExtra("content")!!
        val time = intent.getLongExtra("time", -1)
        val tid: Int = intent.getIntExtra("tid", -1)
    }
}