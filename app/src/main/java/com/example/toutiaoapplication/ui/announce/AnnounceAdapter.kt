package com.example.toutiaoapplication.ui.announce

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.repo.entities.News
import com.example.toutiaoapplication.repo.entities.Top
import com.example.toutiaoapplication.ui.thread.ArticleActivity
import com.example.toutiaoapplication.utils.loadSavedUserInfo
import com.example.toutiaoapplication.utils.transUnixTime
import kotlinx.android.synthetic.main.item_news_article_text.view.*

class AnnounceAdapter(private var data: List<News>, private var presenter: AnnounceContract.Presenter) :
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
            val intent = Intent(parent.context, ArticleActivity::class.java).apply {
                putExtra("title", title)
                putExtra("content", content)
                putExtra("time", time)
                putExtra("tid", tid)
            }
            parent.context.startActivity(intent)
        }
        if (loadSavedUserInfo(parent.context).aid > 1) {
            holder.itemView.setOnLongClickListener {
                val menu = PopupMenu(parent.context, it)
                menu.menuInflater.inflate(R.menu.menu_touch_hold, menu.menu)
                menu.setOnMenuItemClickListener {item ->
                    when (item.itemId) {
                        R.id.threadDelete -> {
                            // fake delete
                            notifyItemRemoved(holder.adapterPosition)
                        }
                        R.id.threadTop -> {
                            // fake top
                            val pos = holder.adapterPosition
                            val topData = Top(tname = data[pos].title, ttime = data[pos].time,
                                uname = "",udesc = "",uid = -1,fname = "",fid = -1,ttop = -1,tcont = "",tid = -1,tdel = -1)
                            presenter.coverTop(topData)
                        }
                    }
                    true
                }
                menu.show()
                true
            }
        }

        return holder
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ThreadViewHolder, position: Int) {
        holder.itemView.tv_extra.text = transUnixTime(data[position].time)
        holder.itemView.tv_title.text = data[position].title
        holder.itemView.tv_abstract.text = data[position].content
    }

    inner class ThreadViewHolder(itemView: CardView) : RecyclerView.ViewHolder(itemView)

    companion object {
        const val TAG = "AnnounceAdapter"
    }
}
