package com.example.toutiaoapplication.ui.login

import com.example.toutiaoapplication.base.BasePresenter
import com.example.toutiaoapplication.base.BaseView

interface LoginContract {
    interface Presenter : BasePresenter {
        fun login(userName: String, password: String)
    }

    interface View : BaseView<Presenter>{
        fun onLoginSuccess()
        fun onLoginFailed()
        fun showInputError()
    }
}