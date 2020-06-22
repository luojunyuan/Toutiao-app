package com.example.toutiaoapplication.ui.setting

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.repo.ApiServers
import com.example.toutiaoapplication.repo.entities.ResponseUser
import com.example.toutiaoapplication.repo.entities.User
import com.example.toutiaoapplication.repo.entities.payload.InfoPayload
import com.example.toutiaoapplication.utils.loadSavedUserInfo
import com.example.toutiaoapplication.utils.toast
import kotlinx.android.synthetic.main.activity_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        val user: User = loadSavedUserInfo(this)

        ApiServers().getApiService().getInfo(user.username)
            .enqueue(object : Callback<ResponseUser> {
                override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                    Log.d(TAG, t.message.toString())
                }

                override fun onResponse(
                    call: Call<ResponseUser>,
                    response: Response<ResponseUser>
                ) {
                    if (response.isSuccessful) {
                        emailAddr.setText(response.body()?.data?.email.toString())
                        descript.setText(response.body()?.data?.descriptor.toString())
                    }
                }

            })


        buttonPost.setOnClickListener {
            val d = descript.text.toString()
            val e = emailAddr.text.toString()
            val payload = InfoPayload(d, e)
            ApiServers().getApiService().updateInfo(payload)
                .enqueue(object : Callback<ResponseUser>{
                    override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                        Log.d(TAG, t.message.toString())
                    }

                    override fun onResponse(
                        call: Call<ResponseUser>,
                        response: Response<ResponseUser>
                    ) {
                        if (response.isSuccessful) {
                            if (response.body()?.ret_code == "1"){
                                toast("数据修改成功")
                            }
                        }
                    }

                })
        }
    }

    companion object {
        const val TAG = "InfoActivity"
    }
}
