package com.example.toutiaoapplication.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.toutiaoapplication.R
import kotlinx.android.synthetic.main.item_news_article_text.view.*

class HomeAdapter(var data: ArrayList<String>) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_news_article_text, parent, false) as CardView

        return HomeViewHolder(itemView)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.itemView.tv_extra.text = data[position]
        holder.itemView.tv_title.text = data[position]
        holder.itemView.tv_abstract.text = data[position]
    }

    class HomeViewHolder(itemView: CardView) : RecyclerView.ViewHolder(itemView)
}
