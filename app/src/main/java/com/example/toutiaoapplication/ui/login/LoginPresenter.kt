package com.example.toutiaoapplication.ui.login

import android.nfc.Tag
import android.os.Looper
import android.os.Handler
import android.util.Log
import com.example.toutiaoapplication.repo.ApiServers
import com.example.toutiaoapplication.repo.entities.Joke
import com.example.toutiaoapplication.utils.isValid
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG = "LoginPresenter"

class LoginPresenter(val view: LoginContract.View) : LoginContract.Presenter {

    override fun login(userName: String, password: String) {
        Log.d(TAG, userName)
        Log.d(TAG, userName.isValid().toString())
        if (userName.isValid() and password.isValid()) {
            uiThread { testLogin() }
        } else view.showInputError()
    }

    private fun testLogin() {
        ApiServers().getApiService().getJokeNull()
            .enqueue(object : Callback<Joke> {
                override fun onFailure(call: Call<Joke>, t: Throwable) {
                    Log.d(TAG, "on failure")
                    uiThread { view.onLoginFailed() }
                }

                override fun onResponse(call: Call<Joke>, response: Response<Joke>) {
                    Log.d(TAG, "on response")
                    uiThread { view.onLoginSuccess() }
                }
            })
    }

    companion object {
        val handler by lazy {
            Handler(Looper.getMainLooper())
        }
    }

    private fun uiThread(f: () -> Unit) {
        handler.post{ f() }
    }

    override fun start() {
        TODO("Not yet implemented")
    }
}