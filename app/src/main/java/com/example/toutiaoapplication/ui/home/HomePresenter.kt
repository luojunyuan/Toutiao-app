package com.example.toutiaoapplication.ui.home

import android.util.Log
import android.widget.Toast
import com.example.toutiaoapplication.repo.ApiServers
import com.example.toutiaoapplication.repo.entities.ResponseNews
import com.example.toutiaoapplication.utils.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenter(private var view: HomeFragment) : HomeContract.Presenter {

    override fun requestData() {//: List<News>
        ApiServers().getApiService().allNews()
            .enqueue(object : Callback<ResponseNews> {
                override fun onFailure(call: Call<ResponseNews>, t: Throwable) {
                    Log.d(TAG, t.toString())
                    uiThread { view.toast(t.message.toString()) }
                }

                override fun onResponse(call: Call<ResponseNews>, response: Response<ResponseNews>) {
                    Log.d(TAG, response.body()?.data.toString())
                    response.body()?.let {
                        uiThread { view.refreshNews(it.data.list) }
                    }
                }
            })
    }

    override fun start() {
    }

    companion object {
        const val TAG = "HomePresenter"
    }
}