package com.example.toutiaoapplication.ui.comment

import com.example.toutiaoapplication.base.BasePresenter
import com.example.toutiaoapplication.base.BaseView
import com.example.toutiaoapplication.repo.entities.Comments

interface CommentContract {
    interface Presenter : BasePresenter {
        fun getComments(tid: String) // v层调用
        fun postComment(threadId: String, content: String)
        // fun getUserByComments(data: List<Comments>)
    }

    interface View : BaseView<Presenter> {
        fun setData(data: List<Comments>) // p层调用 设置到adapter
    }
}