package com.example.toutiaoapplication.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.repo.entities.News
import com.example.toutiaoapplication.ui.detail.DetailActivity
import com.example.toutiaoapplication.utils.toast

class HomeFragment : Fragment(), HomeContract.View {

    override fun refreshNews(data: List<News>) {
        viewAdapter = HomeAdapter(data)
        viewAdapter?.notifyDataSetChanged()
        recyclerView.adapter = viewAdapter
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
        // definite no sense
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
}