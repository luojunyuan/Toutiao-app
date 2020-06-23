package com.example.toutiaoapplication.ui.notice

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.repo.ApiServers
import com.example.toutiaoapplication.repo.entities.Notice
import com.example.toutiaoapplication.repo.entities.ResponseNotice
import com.example.toutiaoapplication.ui.mine.CollectBean
import com.example.toutiaoapplication.ui.mine.CollectDatas
import com.example.toutiaoapplication.ui.mine.NoticeAdapter
import com.example.toutiaoapplication.utils.HelloIntentService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoticeActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)
        title = "历史通知"

        /*找到控件*/
        recyclerView = findViewById(R.id.notice_recycle_view)
        val viewManager = LinearLayoutManager(this)
        recyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
        }

        getData()
    }

    private fun getData() {
        ApiServers().getApiService().getNotice()
            .enqueue(object : Callback<ResponseNotice> {
                override fun onFailure(call: Call<ResponseNotice>, t: Throwable) {
                    Log.d(HelloIntentService.TAG, t.message.toString())
                }

                override fun onResponse(
                    call: Call<ResponseNotice>,
                    response: Response<ResponseNotice>
                ) {
                    Log.d("tag", "sadasdasdasdasdasd")
                    if (response.isSuccessful) {
                        response.body()?.data?.let { setData(it) }
                    }
                }
            })
    }

    private fun setData(data: List<Notice>) {
        Log.d("tag", data.toString())
        val adapter = NoticeAdapter(data)
        recyclerView?.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}