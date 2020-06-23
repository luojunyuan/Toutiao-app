package com.example.toutiaoapplication.ui.thread

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.utils.toast
import com.example.toutiaoapplication.utils.transUnixTime
import kotlinx.android.synthetic.main.fragment_article_content.*
import kotlinx.android.synthetic.main.fragment_article_content.view.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val CONTENT = "content"
private const val TIME = "time"

/**
 * A simple [Fragment] subclass.
 * Use the [ArticleContentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArticleContentFragment : Fragment() {
    private var content: String? = null
    private var time: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            content = it.getString(CONTENT)
            time = it.getLong(TIME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_article_content, container, false)

        rootView.findViewById<TextView>(R.id.tvContent).text = content
        rootView.findViewById<TextView>(R.id.tvTime).text = transUnixTime(time!!)
        rootView.button.setOnClickListener {
            toast("喜欢👍，+1s")
            textView.text = (textView.text.toString().toInt()+1).toString()
        }
        rootView.button2.setOnClickListener {
            toast("不好👎")
            textView.text = (textView.text.toString().toInt()-1).toString()
        }

        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param content Parameter 1.
         * @param time Parameter 2.
         * @return A new instance of fragment ArticleContentFragment.
         */
        @JvmStatic
        fun newInstance(content: String, time: Long) =
            ArticleContentFragment().apply {
                arguments = Bundle().apply {
                    putString(CONTENT, content)
                    putLong(TIME, time)
                }
            }
    }
}