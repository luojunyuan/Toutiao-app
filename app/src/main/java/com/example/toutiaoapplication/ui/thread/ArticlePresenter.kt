package com.example.toutiaoapplication.ui.thread

import android.util.Log
import com.example.toutiaoapplication.base.BasePresenter
import com.example.toutiaoapplication.repo.ApiServers
import com.example.toutiaoapplication.repo.entities.ResponseComments
import com.example.toutiaoapplication.ui.comment.CommentPresenter
import com.example.toutiaoapplication.utils.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticlePresenter(val view: ArticleCommentFragment): BasePresenter {
    override fun start() {

    }

    fun getComments(tid: Int) {
        ApiServers().getApiService().getComments(tid.toString())
            .enqueue(object : Callback<ResponseComments> {
                override fun onFailure(call: Call<ResponseComments>, t: Throwable) {
                    Log.d(CommentPresenter.TAG, t.toString())
                    uiThread { view.toast(t.message.toString()) }
                }

                override fun onResponse(
                    call: Call<ResponseComments>,
                    response: Response<ResponseComments>
                ) {
                    Log.d(CommentPresenter.TAG, response.body()?.data?.list.toString())
                    response.body()?.let {
                        uiThread { view.setAdapter(it.data.list) }
                    }
                }
            })
    }
}