package com.example.toutiaoapplication.ui.thread

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.repo.entities.Comments
import com.example.toutiaoapplication.ui.comment.CommentAdapter

/**
 * A fragment representing a list of Items.
 */
class ArticleCommentFragment : Fragment() {

    private var tid = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            tid = it.getInt(threadId)
        }
    }

    lateinit var view: RecyclerView
    lateinit var adapter: CommentAdapter
    lateinit var presenter: ArticlePresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.activity_comments, container, false)
        view = rootView.findViewById(R.id.commentList)
        view.layoutManager = LinearLayoutManager(context)

        presenter = ArticlePresenter(this)
        presenter.getComments(tid)

        return rootView
    }

    fun setAdapter(data: List<Comments>) {
        adapter = CommentAdapter(data)
        adapter.notifyDataSetChanged()
        view.adapter = adapter
    }

    companion object {

        const val threadId = "tid"

        @JvmStatic
        fun newInstance(tid: Int) =
            ArticleCommentFragment().apply {
                arguments = Bundle().apply {
                    putInt(threadId, tid)
                }
            }
    }
}