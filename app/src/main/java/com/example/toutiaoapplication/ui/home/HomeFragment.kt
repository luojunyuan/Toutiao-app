package com.example.toutiaoapplication.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.repo.entities.News
import com.example.toutiaoapplication.repo.entities.Top
import com.example.toutiaoapplication.ui.announce.AnnounceAdapter
import com.example.toutiaoapplication.ui.announce.AnnounceContract
import com.example.toutiaoapplication.ui.announce.AnnouncePresenter
import com.example.toutiaoapplication.ui.search.SearchActivity
import com.example.toutiaoapplication.ui.thread.ArticleActivity
import com.example.toutiaoapplication.ui.thread.NewThreadActivity
import com.example.toutiaoapplication.utils.isAlreadyLogged
import com.example.toutiaoapplication.utils.loadSavedUserInfo
import com.example.toutiaoapplication.utils.toast
import com.example.toutiaoapplication.utils.transUnixTime
import java.util.*
import kotlin.concurrent.schedule

class HomeFragment : Fragment(), HomeContract.View {

    override lateinit var presenter: HomeContract.Presenter

    override fun refreshNews(data: List<News>) {
        viewAdapter = HomeAdapter(data, presenter)
        viewAdapter?.notifyDataSetChanged()
        recyclerView.adapter = viewAdapter
        refresh.isRefreshing = false
    }

    override fun setTop(data: Top) {
        topReceiveData = data
        val complexFit = "置顶 " + transUnixTime(data.ttime)
        topExtra.text = complexFit
        topTitle.text = data.tname
    }

    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView
    private var viewAdapter: HomeAdapter? = null
    private lateinit var topExtra: TextView
    private lateinit var topTitle: TextView
    private lateinit var topReceiveData: Top
    private lateinit var refresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        initData()
        initView(rootView)

        return rootView
    }

    private fun initData() {
        presenter = HomePresenter(this)
        viewManager = LinearLayoutManager(context)

        presenter.start()
    }

    private fun initView(view: View) {
        // 设置下拉刷新
        refresh = view.findViewById(R.id.refresh_layout)
        handlerDownPullUpdate(refresh)

        // 初始化topView
        topExtra = view.findViewById(R.id.tv_extra)
        topTitle = view.findViewById(R.id.tv_title)
        view.findViewById<CardView>(R.id.topView).setOnClickListener {
            val intent = Intent(context, ArticleActivity::class.java).apply {
                putExtra("title", topReceiveData.tname)
                putExtra("content", topReceiveData.tcont)
                putExtra("time", topReceiveData.ttime)
                putExtra("tid", topReceiveData.tid)
            }
            startActivity(intent)
        }

        // 设置recyclerview
        recyclerView = view.findViewById<RecyclerView>(R.id.home_recycler_view).apply {
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        super.onCreateOptionsMenu(menu, inflater)

        val new = menu.findItem(R.id.newThread)
        // maybe -1 on Logout status
        new.isVisible = this.context?.let { loadSavedUserInfo(it).aid } != 1
        new.setOnMenuItemClickListener {
            if (isAlreadyLogged(this.requireContext())) {
                startActivity(Intent(this.context, NewThreadActivity::class.java))
            } else toast("请先登录")
            true
        }

        val search = menu.findItem((R.id.search))
        search.setOnMenuItemClickListener {
            startActivity(Intent(this.context, SearchActivity::class.java))
            true
        }
    }
}

// 舍不得删的代码
// definitely make no sense
//        recyclerView.addOnItemTouchListener(RecyclerItemClickListener(
//            rootView.context, recyclerView, object : RecyclerItemClickListener.OnItemClickListener {
//
//                override fun onItemClick(view: View, position: Int) {
//                    val intent = Intent(activity, DetailActivity::class.java)
//                    val title = view.findViewById<TextView>(R.id.tv_title).text.toString()
//
//                    startActivity(intent)
//                }
//                override fun onItemLongClick(view: View?, position: Int) {
//                    toast("点击打开")
//                }
//            }))