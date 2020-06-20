package com.example.toutiaoapplication.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.repo.ApiServers
import com.example.toutiaoapplication.repo.entities.ResponseNews
import com.example.toutiaoapplication.repo.entities.ThreadPayload
import com.example.toutiaoapplication.ui.detail.CommentPresenter
import com.example.toutiaoapplication.utils.loadSavedUserInfo
import com.example.toutiaoapplication.utils.toast
import kotlinx.android.synthetic.main.activity_new_thread.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates

class NewThreadActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private var selectForum by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_thread)

        val userInfo = loadSavedUserInfo(this)

        // https://developer.android.com/guide/topics/ui/controls/spinner.html
        val spinner: Spinner = findViewById(R.id.forum_spinner)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.forum_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = this

        buttonPost.setOnClickListener {
            // 确保内容分区无误
            val title = insert_title.text.toString()
            val content = contentArea.text.toString()
            val userId = userInfo.uid
            if (title == "" || content == ""){
                toast("不能发表空内容和标题！")
            }
            val payload = ThreadPayload(fid = selectForum, tcont = content,
                                        tname = title, uid = userId)
            ApiServers().getApiService().createThread(payload)
                .enqueue(object : Callback<ResponseNews> {
                    override fun onFailure(call: Call<ResponseNews>, t: Throwable) {
                        Log.d(TAG, t.toString())
                        toast(t.message.toString())
                    }

                    override fun onResponse(
                        call: Call<ResponseNews>,
                        response: Response<ResponseNews>
                    ) {
                        Log.d(TAG, response.code().toString())
                        Log.d(TAG, response.body().toString())
                        if (response.body()?.code == 0) {
                            toast("发表成功！")
                            finish()
                        } else toast("未知错误")
                    }
                })
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        // Another interface callback
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        selectForum = position + 1
        Log.d(TAG, "选择的分区编号: $selectForum")
    }

    companion object {
        const val TAG = "NewThreadActivity"
    }
}
