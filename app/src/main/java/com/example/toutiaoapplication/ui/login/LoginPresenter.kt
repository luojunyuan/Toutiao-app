package com.example.toutiaoapplication.ui.login

import android.util.Log
import com.example.toutiaoapplication.repo.ApiServers
import com.example.toutiaoapplication.repo.entities.payload.LoginPayload
import com.example.toutiaoapplication.repo.entities.ResponseUser
import com.example.toutiaoapplication.utils.HeaderInterceptor
import com.example.toutiaoapplication.utils.isValid
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(val view: LoginContract.View) : LoginContract.Presenter {

    override fun login(userName: String, password: String) {
        if (userName.isValid() and password.isValid()) {
            val payload =
                LoginPayload(
                    userName,
                    password
                )
            uiThread { testLogin(payload) }
        } else view.showInputError()
    }

    private fun testLogin(payload: LoginPayload) {
        ApiServers().getApiService().loginUser(payload)
            .enqueue(object : Callback<ResponseUser> {
                override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                    Log.d(TAG, "on failure $t")
                    uiThread { view.onLoginFailed() }
                }

                override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                    Log.d(TAG, "on response: $response")
                    response.body()?.let {
                        Log.d(TAG, "返回: $it")
                        when (it.ret_code) {
                            "-1" -> uiThread { view.errorInfo() }
                            // "0" -> uiThread { view.onLoginSuccess(it.data) }
                            "1" -> {
                                HeaderInterceptor.cookie = response.headers()["set-cookie"]
                                Log.e("NetWork=>headers", HeaderInterceptor.cookie!!)
                                it.data.cookie = HeaderInterceptor.cookie!!
                                uiThread { view.onLoginSuccess(it.data) }
                            }
                            else -> Log.d(TAG, it.ret_code)
                        }
                    }
                }
            })
    }

    override fun start() {
        TODO("Not yet implemented")
    }

    companion object {
        const val TAG = "LoginPresenter"
    }
}