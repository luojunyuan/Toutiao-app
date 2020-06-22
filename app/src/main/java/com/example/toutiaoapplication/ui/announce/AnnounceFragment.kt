package com.example.toutiaoapplication.ui.announce

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.repo.entities.News
import com.example.toutiaoapplication.repo.entities.Top
import com.example.toutiaoapplication.ui.home.HomeAdapter
import com.example.toutiaoapplication.ui.thread.AnotherThreadActivity
import com.example.toutiaoapplication.utils.transUnixTime
import kotlinx.android.synthetic.main.fragment_announce.*
import java.util.*
import kotlin.concurrent.schedule

class AnnounceFragment : Fragment(), AnnounceContract.View {
    override lateinit var presenter: AnnounceContract.Presenter

    override fun refreshNews(data: List<News>) {
        viewAdapter = AnnounceAdapter(data)
        viewAdapter?.notifyDataSetChanged()
        recyclerView.adapter = viewAdapter
        refresh.isRefreshing = false
    }

    override fun setTop(data: Top) {
        topReceiveData = data
        val complexFit = "置顶" + transUnixTime(data.ttime)
        topExtra.text = complexFit
        topTitle.text = data.tname
    }

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView
    private var viewAdapter: AnnounceAdapter? = null
    private lateinit var topExtra: TextView
    private lateinit var topTitle: TextView
    private lateinit var topReceiveData: Top
    private lateinit var refresh: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_announce, container, false)

        initData()
        initView(rootView)

        return rootView
    }

    private fun initView(view: View) {
        // 设置下拉刷新
        refresh = view.findViewById(R.id.refresh_layout)
        handlerDownPullUpdate(refresh)

        // 初始化topView
        topExtra = view.findViewById(R.id.tv_extra)
        topTitle = view.findViewById(R.id.tv_title)
        view.findViewById<CardView>(R.id.topView).setOnClickListener {
            val intent = Intent(context, AnotherThreadActivity::class.java).apply {
                putExtra("title", topReceiveData.tname)
                putExtra("content", topReceiveData.tcont)
                putExtra("time", topReceiveData.ttime)
                putExtra("tid", topReceiveData.tid)
            }
            startActivity(intent)
        }

        // 设置recyclerview
        recyclerView = view.findViewById<RecyclerView>(R.id.announce_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
        }
    }

    private fun handlerDownPullUpdate(refresh: SwipeRefreshLayout) {
        refresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent)
        refresh.isEnabled = true
        refresh.setOnRefreshListener {
            // 执行刷新数据的操作
            presenter.requestData()
        }
    }

    private fun initData() {
        presenter = AnnouncePresenter(this)
        viewManager = LinearLayoutManager(context)

        presenter.start()
    }

    companion object {
        const val TAG = "AnnounceFragment"
    }
}