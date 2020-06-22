package com.example.toutiaoapplication.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.repo.ApiServers
import com.example.toutiaoapplication.repo.entities.News
import com.example.toutiaoapplication.repo.entities.ResponseNews
import com.example.toutiaoapplication.ui.home.HomeAdapter
import com.example.toutiaoapplication.utils.toast
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val viewManager = LinearLayoutManager(this.applicationContext)
        recyclerView = findViewById<RecyclerView>(R.id.itemList).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
        }

        buttonSearch.setOnClickListener {
            if (titleEditText.text.toString() != "") {
                queryThread(titleEditText.text.toString())
            } else toast("尚未输入搜索内容")
        }
    }

    private fun queryThread(query: String) {
        Log.d(TAG, "查询的字符串是 $query")
        ApiServers().getApiService().searchWord(query)
            .enqueue(object : Callback<ResponseNews> {
                override fun onFailure(call: Call<ResponseNews>, t: Throwable) {
                    Log.d(TAG, t.toString())
                    toast(t.message.toString())
                }

                override fun onResponse(
                    call: Call<ResponseNews>,
                    response: Response<ResponseNews>
                ) {
                    if (response.isSuccessful) {
                        Log.d(TAG, response.body()?.code.toString())
                        Log.d(TAG, response.body()?.data.toString())
                        response.body()?.data?.list?.let { refresh(it) }
                    } else toast("something wrong")
                }

            })
    }

    fun refresh(data: List<News>) {
        val adapter = HomeAdapter(data)
        adapter.notifyDataSetChanged()
        recyclerView.adapter = adapter
    }

    companion object {
        const val TAG = "SearchActivity"
    }
}