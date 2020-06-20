package com.example.toutiaoapplication.ui.setting

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.repo.ApiServers
import com.example.toutiaoapplication.repo.entities.logoutResponse
import com.example.toutiaoapplication.ui.home.HomePresenter
import com.example.toutiaoapplication.utils.clearUserInfo
import com.example.toutiaoapplication.utils.isAlreadyLogged
import com.example.toutiaoapplication.utils.toast
import kotlinx.android.synthetic.main.activity_user_setting.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserSettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_setting)

        logoutView.setOnClickListener {
            ApiServers().getApiService().logout()
                .enqueue(object : Callback<logoutResponse>{
                    override fun onFailure(call: Call<logoutResponse>, t: Throwable) {
                        Log.d(TAG, t.toString())
                        toast(t.message.toString())
                    }

                    override fun onResponse(
                        call: Call<logoutResponse>,
                        response: Response<logoutResponse>
                    ) {
                        toast("登出成功！")
                        clearUserInfo(applicationContext)
                        finish()
                    }

                })
        }
    }

    companion object {
        const val TAG = "UserSettingActivity"
    }
}