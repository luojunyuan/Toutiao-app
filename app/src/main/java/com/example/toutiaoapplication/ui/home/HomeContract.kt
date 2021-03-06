package com.example.toutiaoapplication.ui.home

import androidx.recyclerview.widget.RecyclerView
import com.example.toutiaoapplication.base.BasePresenter
import com.example.toutiaoapplication.base.BaseView
import android.view.View
import com.example.toutiaoapplication.repo.entities.News
import com.example.toutiaoapplication.repo.entities.Top

interface HomeContract {
    interface Presenter : BasePresenter {
        // 被view层调用，用来向m层请求数据
        fun requestData()
        // 请求置顶（置顶只有一个，和分区无关）
        fun requestTop()
        fun coverTop(data: Top)
    }

    interface View : BaseView<Presenter> {
        // 被presenter层调用，用来给view层设置数据
        // data 是返回该分区的文章
        fun refreshNews(data: List<News>)
        // setTop 重新设置置顶item数据
        fun setTop(data: Top)
    }
}