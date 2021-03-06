package com.example.toutiaoapplication.ui.register
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.repo.ApiServers
import com.example.toutiaoapplication.repo.entities.payload.RegisterPayload
import com.example.toutiaoapplication.repo.entities.ResponseUser
import com.example.toutiaoapplication.utils.isValid
import com.example.toutiaoapplication.utils.toast
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register.setOnClickListener{
            val uname = username.text.toString()
            val upass = password_1.text.toString()
            if (uname.isValid() and upass.isValid()) {
                if (password_1.text.trim().toString() == password_2.text.trim().toString()) {
                    // 发送信息到api
                    val payload =
                        RegisterPayload(
                            uname = username.text.trim().toString(),
                            upass = password_1.text.trim().toString(),
                            umail = email.text.trim().toString()
                        )
                    ApiServers().getApiService().register(payload)
                        .enqueue(object : Callback<ResponseUser>{
                            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                                Log.d(TAG, t.toString())
                                toast(t.message.toString())
                            }

                            override fun onResponse(
                                call: Call<ResponseUser>,
                                response: Response<ResponseUser>
                            ) {
                                Log.d(TAG, response.body()?.data.toString())
                                toast("注册成功")
                                finish()
                            }
                        })
                } else toast("两次输入密码不一致")
            } else toast("用户名或密码输入不规范")

        }
    }

    companion object {
        const val TAG = "RegisterActivity"
    }
}