package com.example.toutiaoapplication.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.repo.entities.News
import com.example.toutiaoapplication.ui.search.SearchActivity
import com.example.toutiaoapplication.ui.thread.NewThreadActivity
import com.example.toutiaoapplication.utils.isAlreadyLogged
import com.example.toutiaoapplication.utils.loadSavedUserInfo
import com.example.toutiaoapplication.utils.toast
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*
import kotlin.concurrent.schedule

class HomeFragment : Fragment(), HomeContract.View {

    override fun refreshNews(data: List<News>) {
        viewAdapter = ThreadAdapter(data)
        viewAdapter?.notifyDataSetChanged()
        recyclerView.adapter = viewAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override lateinit var presenter: HomeContract.Presenter

    private lateinit var progressBar: ProgressBar
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView
    private var viewAdapter: ThreadAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        presenter = HomePresenter(this)
        initView(rootView)
        // initData
        presenter.requestData()

        return rootView
    }

    private fun initView(rootView: View) {
        // progressBar = rootView.findViewById(R.id.home_progress_bar)
        // progressBar.visibility = View.INVISIBLE

        viewManager = LinearLayoutManager(context)
        recyclerView = rootView.findViewById<RecyclerView>(R.id.home_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
        }
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
        val refresh = rootView.findViewById<SwipeRefreshLayout>(R.id.refresh_layout)
        // 处理下拉刷新
        handlerDownPullUpdate(refresh)
    }

    private fun handlerDownPullUpdate(refresh: SwipeRefreshLayout) {
        refresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent)
        refresh.isEnabled = true
        refresh.setOnRefreshListener {
            // 执行刷新数据的操作
            presenter.requestData()
            // val handler : Handler = Handler {
            //
            // }
            Timer("SettingUp", false).schedule(1000) {
                refresh.isRefreshing = false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        super.onCreateOptionsMenu(menu, inflater)

        val new = menu.findItem(R.id.newThread)
        new.setOnMenuItemClickListener {
            if (isAlreadyLogged(this.requireContext())) {
                startActivity(Intent(this.context, NewThreadActivity::class.java))
            } else toast("请先登录")
            true
        }

        val search = menu.findItem((R.id.search))
        search.setOnMenuItemClickListener {
            if (this.context?.let { loadSavedUserInfo(it).aid } == 1){
                toast("无权发帖！")
            }
            startActivity(Intent(this.context, SearchActivity::class.java))
            true
        }


    }
}