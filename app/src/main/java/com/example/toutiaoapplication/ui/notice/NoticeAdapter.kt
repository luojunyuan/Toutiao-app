package com.example.toutiaoapplication.ui.mine

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.repo.entities.Notice
import com.example.toutiaoapplication.utils.transUnixTime
import kotlinx.android.synthetic.main.item_notice.view.*

class NoticeAdapter(private val mData: List<Notice>) :
    RecyclerView.Adapter<NoticeAdapter.InnerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerHolder {
        val view = View.inflate(parent.context, R.layout.item_notice, null)
        return InnerHolder(view)
    }

    override fun onBindViewHolder(holder: InnerHolder, position: Int) {
        holder.itemView.item_notice_date.text = transUnixTime(mData[position].ntime)
        holder.itemView.item_notice.text = mData[position].ncont
    }

    override fun getItemCount(): Int = mData.size

    inner class InnerHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
