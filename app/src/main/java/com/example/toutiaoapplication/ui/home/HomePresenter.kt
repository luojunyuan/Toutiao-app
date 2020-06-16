package com.example.toutiaoapplication.ui.home

import androidx.recyclerview.widget.RecyclerView

class HomePresenter(private var view: HomeFragment) : HomeContract.Presenter {
    override fun prepareRecyclerViewAdapter(recyclerView: RecyclerView) {
        val data = arrayListOf<String>("aaaaaa", "bbbbbb")
        view.viewAdapter = HomeAdapter(data)
        recyclerView.adapter = view.viewAdapter
        view.viewAdapter?.notifyDataSetChanged()
    }

    override fun start() {
    }


}