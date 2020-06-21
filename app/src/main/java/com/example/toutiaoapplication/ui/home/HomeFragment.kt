package com.example.toutiaoapplication.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.repo.entities.News
import com.example.toutiaoapplication.utils.isAlreadyLogged
import com.example.toutiaoapplication.utils.toast

class HomeFragment : Fragment(), HomeContract.View {

    override fun refreshNews(data: List<News>) {
        viewAdapter = HomeAdapter(data)
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
    private var viewAdapter: HomeAdapter? = null

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
        progressBar = rootView.findViewById(R.id.home_progress_bar)
        progressBar.visibility = View.INVISIBLE

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
            // startActivity(Intent(this.context, ))
            true
        }
    }
}