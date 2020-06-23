package com.example.toutiaoapplication.ui.thread

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.toutiaoapplication.MainActivity
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.repo.ApiServers
import com.example.toutiaoapplication.repo.entities.Comments
import com.example.toutiaoapplication.repo.entities.ResponseComments
import com.example.toutiaoapplication.repo.entities.payload.CommentPayload
import com.example.toutiaoapplication.ui.comment.CommentAdapter
import com.example.toutiaoapplication.ui.comment.CommentPresenter
import com.example.toutiaoapplication.ui.comment.CommentsActivity
import com.example.toutiaoapplication.utils.loadSavedUserInfo
import com.example.toutiaoapplication.utils.toast
import kotlinx.android.synthetic.main.activity_comments.*
import kotlinx.android.synthetic.main.activity_comments.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        rootView.addButton.setOnClickListener {
            // val container = findViewById<ViewGroup>(R.id.commentList)
            val mLayoutInflater = LayoutInflater.from(requireContext())
            val view = mLayoutInflater.inflate(R.layout.dialog_comment, null) // container

            val builder = AlertDialog.Builder(requireContext())
            val dialog = builder
                .setTitle("评论：")
                .setView(view)
                .setNegativeButton("取消") { _, _ ->
                    toast("取消发送")
                }
                .setPositiveButton("确定") { _, _ ->
                    val comment = view.findViewById<EditText>(R.id.commentContent).text.toString()
                    postComment(tid, comment)
                }
                .create() // 创建AlertDialog对象
            dialog.show()
        }

        return rootView
    }

    private fun postComment(threadId: Int, content: String) {
        val userId = loadSavedUserInfo(MainActivity.instance.applicationContext).uid
        val payload =
            CommentPayload(
                tid = threadId,
                uid = userId,
                ccont = content
            )
        ApiServers().getApiService().postComment(payload)
            .enqueue(object : Callback<ResponseComments> {
                override fun onFailure(call: Call<ResponseComments>, t: Throwable) {
                    Log.d(CommentPresenter.TAG, t.toString())
                    toast(t.message.toString())
                }

                override fun onResponse(
                    call: Call<ResponseComments>,
                    response: Response<ResponseComments>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            val retCode = it.code
                            // Log.d(TAG, it.data.list.toString())
                            if (retCode == 0) {
                                toast("成功发送")
                            } else toast("return code: $retCode")
                        }

                    }
                }
            })
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