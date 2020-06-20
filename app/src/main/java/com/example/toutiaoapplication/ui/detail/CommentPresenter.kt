package com.example.toutiaoapplication.ui.detail

import android.util.Log
import com.example.toutiaoapplication.repo.ApiServers
import com.example.toutiaoapplication.repo.entities.ResponseComments
import com.example.toutiaoapplication.utils.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentPresenter(private var view: CommentsActivity) : CommentContract.Presenter {
    override fun getComments(tid: String) {
        ApiServers().getApiService().getComments(tid)
            .enqueue(object : Callback<ResponseComments> {
                override fun onFailure(call: Call<ResponseComments>, t: Throwable) {
                    Log.d(TAG, t.toString())
                    uiThread { view.toast(t.message.toString()) }
                }

                override fun onResponse(
                    call: Call<ResponseComments>,
                    response: Response<ResponseComments>
                ) {
                    Log.d(TAG, response.body()?.data?.list.toString())
                    response.body()?.let {
                        uiThread { view.setData(it.data.list) }
                    }
                }

            })
    }

    override fun start() {
        TODO("Not yet implemented")
    }

    companion object {
        const val TAG = "CommentPresenter"
    }
}