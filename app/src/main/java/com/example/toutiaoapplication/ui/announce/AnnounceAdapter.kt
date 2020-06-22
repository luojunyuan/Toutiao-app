package com.example.toutiaoapplication.ui.announce

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.repo.entities.News
import com.example.toutiaoapplication.ui.detail.DetailActivity
import com.example.toutiaoapplication.ui.thread.AnotherThreadActivity
import com.example.toutiaoapplication.utils.transUnixTime
import kotlinx.android.synthetic.main.item_news_article_text.view.*

class AnnounceAdapter(private var data: List<News>) :
    RecyclerView.Adapter<AnnounceAdapter.ThreadViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThreadViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_news_article_text, parent, false) as CardView

        val holder = ThreadViewHolder(itemView)
        holder.itemView.setOnClickListener{
            val position = holder.adapterPosition
            val title = data[position].title
            val content = data[position].content
            val time = data[position].time
            val tid = data[position].tid
            val intent = Intent(parent.context, AnotherThreadActivity::class.java).apply {
                putExtra("title", title)
                putExtra("content", content)
                putExtra("time", time)
                putExtra("tid", tid)
            }
            parent.context.startActivity(intent)
        }

        return holder
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ThreadViewHolder, position: Int) {
        holder.itemView.tv_extra.text = transUnixTime(data[position].time)
        holder.itemView.tv_title.text = data[position].title
        holder.itemView.tv_abstract.text = data[position].content
    }

    class ThreadViewHolder(itemView: CardView) : RecyclerView.ViewHolder(itemView)

    companion object {
        const val TAG = "AnnounceAdapter"
    }
}
