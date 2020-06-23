package com.example.toutiaoapplication.ui.home

import android.util.Log
import android.widget.Toast
import com.example.toutiaoapplication.repo.ApiServers
import com.example.toutiaoapplication.repo.entities.News
import com.example.toutiaoapplication.repo.entities.ResponseNews
import com.example.toutiaoapplication.repo.entities.ResponseSingleNew
import com.example.toutiaoapplication.repo.entities.Top
import com.example.toutiaoapplication.ui.announce.AnnouncePresenter
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
                    if (response.isSuccessful){
                        response.body()?.let {
                            val newList = mutableListOf<News>()
                            for (thread in it.data.list) {
                                if (thread.del == 1 || thread.top == 1) {
                                    //
                                } else newList.add(thread)
                            }
                            uiThread { view.refreshNews(newList) }
                        }
                    } else uiThread { view.toast("Response Error") }
                }
            })
    }

    override fun requestTop() {
        ApiServers().getApiService().getTop()
            .enqueue(object : Callback<ResponseSingleNew>{
                override fun onFailure(call: Call<ResponseSingleNew>, t: Throwable) {
                    Log.d(AnnouncePresenter.TAG, t.message.toString())
                    uiThread { view.toast("Retrofit onFailure") }
                }

                override fun onResponse(
                    call: Call<ResponseSingleNew>,
                    response: Response<ResponseSingleNew>
                ) {
                    if (response.isSuccessful){
                        response.body()?.let {
                            uiThread { view.setTop(it.data[0]) }
                        }
                    } else uiThread { view.toast("Response Error") }
                }
            })
    }

    override fun coverTop(data: Top) {
        view.setTop(data)
    }

    override fun start() {
        requestTop()
        requestData()
    }

    companion object {
        const val TAG = "HomePresenter"
    }
}