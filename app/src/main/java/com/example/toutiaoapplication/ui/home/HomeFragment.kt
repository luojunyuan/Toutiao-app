package com.example.toutiaoapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.toutiaoapplication.R

class HomeFragment : Fragment() {

    lateinit var progressBar: ProgressBar
    lateinit var viewManager: RecyclerView.LayoutManager
    lateinit var recyclerView: RecyclerView
    var viewAdapter: HomeAdapter? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        progressBar = rootView.findViewById(R.id.home_progress_bar)
        progressBar.visibility = View.INVISIBLE

        viewManager = LinearLayoutManager(context)
        val data = arrayListOf<String>("aaaaaa", "bbbbbb")
        viewAdapter = HomeAdapter(data)
        recyclerView = rootView.findViewById<RecyclerView>(R.id.home_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        return rootView
    }
}