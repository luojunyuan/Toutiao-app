package com.example.toutiaoapplication.ui.home

import androidx.recyclerview.widget.RecyclerView
import com.example.toutiaoapplication.base.BasePresenter
import com.example.toutiaoapplication.base.BaseView
import android.view.View
import com.example.toutiaoapplication.repo.entities.News

interface HomeContract {
    interface Presenter : BasePresenter {
        fun requestData()//: List<News>
    }

    interface View : BaseView<Presenter> {
        fun refreshNews(data: List<News>)
    }
}