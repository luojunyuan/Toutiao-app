package com.example.toutiaoapplication.ui.collect

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.ui.mine.CollectBean
import com.example.toutiaoapplication.ui.mine.CollectDatas
import com.example.toutiaoapplication.ui.mine.ListAdapter
import java.util.*

class CollectActivity : AppCompatActivity() {
    private var list: RecyclerView? = null
    private var mdata: MutableList<CollectBean>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collect)
        /*找到控件*/
        list = findViewById(R.id.collect_recycle_view)
        title = "按时间排序"
        /**准备数据 */
        /***
         * 模拟数据
         */
        initData()
    }

    /***这个方法用于模拟数据 */
    private fun initData() {
        //创建数据集合
        mdata = ArrayList()
        //创建模拟数据
        for (i in CollectDatas.dates.indices) {
            val data = CollectBean(date= CollectDatas.dates[i],title = CollectDatas.titles[i])
            //data.date = CollectDatas.dates[i]
            //data.title = CollectDatas.titles[i]
            //添加到集合里
            (mdata as ArrayList<CollectBean>).add(data)
        }
        val layoutManager = LinearLayoutManager(this)
        list!!.layoutManager = layoutManager
        //创建适配器
        val adapter =
            ListAdapter(mdata)
        //设置到recycleView里面去
        list!!.adapter = adapter
    }

}