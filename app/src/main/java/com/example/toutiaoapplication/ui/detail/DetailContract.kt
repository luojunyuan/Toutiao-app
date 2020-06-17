package com.example.toutiaoapplication.ui.detail

import com.example.toutiaoapplication.base.BasePresenter
import com.example.toutiaoapplication.base.BaseView

interface DetailContract {
    interface Presenter : BasePresenter

    interface View : BaseView<Presenter>
}