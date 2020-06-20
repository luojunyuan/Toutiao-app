package com.example.toutiaoapplication.ui.mine

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.toutiaoapplication.R

class ListAdapter(private val mdata: List<CollectBean>?) :
    RecyclerView.Adapter<ListAdapter.InnerHolder>() {

    /**
     * 用于创建条目的view
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InnerHolder {

        //传进的view就是条目的界面
        /*
        1.拿到view
        2.创建InnerHolder
        */
        val view =
            View.inflate(parent.context, R.layout.item_listview, null)
        return InnerHolder(view)
    }

    /**
     * 这个方法时用于绑定holder，一般用来设置数据
     */
    override fun onBindViewHolder(
        holder: InnerHolder,
        position: Int
    ) {
        holder.setData(mdata!![position])
    }

    override fun getItemCount(): Int {
        return mdata?.size ?: 0
    }

    inner class InnerHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val mdate: TextView
        private val mtitle: TextView

        //用于设置数据
        fun setData(collectBean: CollectBean) {
            //设置数据
            mdate.text = collectBean.date
            mtitle.text = collectBean.title
        }

        init {

            //找到条目控件
            mdate = itemView.findViewById<View>(R.id.item_list_date) as TextView
            mtitle = itemView.findViewById<View>(R.id.item_list_title) as TextView
        }
    }

}