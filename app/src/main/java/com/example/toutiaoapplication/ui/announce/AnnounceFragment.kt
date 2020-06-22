package com.example.toutiaoapplication.ui.announce

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.repo.entities.News
import com.example.toutiaoapplication.ui.home.HomeAdapter
import com.example.toutiaoapplication.utils.transUnixTime
import java.util.*
import kotlin.concurrent.schedule

class AnnounceFragment : Fragment(), AnnounceContract.View {
    override lateinit var presenter: AnnounceContract.Presenter

    override fun refreshNews(data: List<News>) {

    }

    override fun setTop(data: News) {
        val complexFit = "置顶" + transUnixTime(data.time)
        topExtra.text = complexFit
        topTitle.text = data.title
    }

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView
    private var viewAdapter: HomeAdapter? = null
    private lateinit var topExtra: TextView
    private lateinit var topTitle: TextView

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
        val refresh = view.findViewById<SwipeRefreshLayout>(R.id.refresh_layout)
        handlerDownPullUpdate(refresh)

        // 初始化topView
        topExtra = view.findViewById(R.id.tv_extra)
        topTitle = view.findViewById(R.id.tv_title)

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
            Timer("SettingUp", false).schedule(1000) {
                refresh.isRefreshing = false
            }
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