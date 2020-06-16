package com.example.toutiaoapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.base.BaseView

class HomeFragment : Fragment(), BaseView<HomeContract.Presenter> {

    companion object {
        private var instance: HomeFragment? = null
    }
    override var presenter: HomeContract.Presenter
        get() = HomePresenter(this)
        set(value) {}

    private lateinit var progressBar: ProgressBar
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView
    var viewAdapter: HomeAdapter? = null

    fun getInstance(): HomeFragment {
        if (instance == null) {
            instance = HomeFragment()
        }
        return instance as HomeFragment
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        initView(rootView)
        initData()

        return rootView
    }

    private fun initData() {
        presenter.prepareRecyclerViewAdapter(recyclerView)
    }

    private fun initView(rootView: View) {
        progressBar = rootView.findViewById(R.id.home_progress_bar)
        progressBar.visibility = View.INVISIBLE

        viewManager = LinearLayoutManager(context)
        recyclerView = rootView.findViewById<RecyclerView>(R.id.home_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
        }
    }
}