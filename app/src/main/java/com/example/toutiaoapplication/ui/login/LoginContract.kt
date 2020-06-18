package com.example.toutiaoapplication.ui.login

import com.example.toutiaoapplication.base.BasePresenter
import com.example.toutiaoapplication.base.BaseView
import com.example.toutiaoapplication.repo.entities.User

interface LoginContract {
    interface Presenter : BasePresenter {
        fun login(userName: String, password: String)
    }

    interface View : BaseView<Presenter>{
        fun onLoginSuccess(user: User)
        fun onLoginFailed()
        fun showInputError()
        fun errorInfo()
    }
}