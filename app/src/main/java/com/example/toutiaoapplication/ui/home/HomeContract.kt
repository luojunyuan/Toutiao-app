package com.example.toutiaoapplication.ui.home

import androidx.recyclerview.widget.RecyclerView
import com.example.toutiaoapplication.base.BasePresenter
import com.example.toutiaoapplication.base.BaseView

interface HomeContract {
    interface Presenter : BasePresenter {
        fun prepareRecyclerViewAdapter(recyclerView: RecyclerView)
    }

    interface View : BaseView<Presenter>
}