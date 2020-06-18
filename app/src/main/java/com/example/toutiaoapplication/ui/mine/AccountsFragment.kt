package com.example.toutiaoapplication.ui.mine

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.repo.entities.User
import com.example.toutiaoapplication.ui.login.LoginActivity
import com.example.toutiaoapplication.utils.isAlreadyLogged
import com.example.toutiaoapplication.utils.loadSavedUserInfo
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_accounts.*

class AccountsFragment : Fragment(), View.OnClickListener{
    private var loginState = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 判断是不是已经登陆过了
        loginState = isAlreadyLogged(this.requireContext())
        if (loginState) {
            val userInfo: User = loadSavedUserInfo(this.requireContext())
            // 做一些修改界面的操作
            ll_top.text = userInfo.username
        }
        Log.d(TAG, "待测试，$loginState")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_accounts, container, false)
        val tv = rootView.findViewById<TextView>(R.id.ll_top)
        tv.setOnClickListener(this)
        return rootView
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.ll_top -> if (!loginState) startActivity(Intent(this.context, LoginActivity::class.java))
        }
    }

    companion object {
        const val TAG = "AccountsFragment"
    }
}