package com.example.toutiaoapplication.ui.mine

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.ListFragment
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.repo.entities.User
import com.example.toutiaoapplication.ui.collect.CollectActivity
import com.example.toutiaoapplication.ui.login.LoginActivity
import com.example.toutiaoapplication.ui.notice.NoticeActivity
import com.example.toutiaoapplication.ui.setting.UserSettingActivity
import com.example.toutiaoapplication.utils.isAlreadyLogged
import com.example.toutiaoapplication.utils.loadSavedUserInfo
import com.example.toutiaoapplication.utils.toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_accounts.*
import kotlinx.android.synthetic.main.fragment_accounts.view.*

class AccountsFragment : Fragment(), View.OnClickListener{
    private var loginState = false
    private lateinit var tv: TextView

    override fun onStart() {
        super.onStart()
        // 判断是不是已经登陆了
        loginState = isAlreadyLogged(this.requireContext())
        if (loginState) {
            val userInfo: User = loadSavedUserInfo(this.requireContext())
            // 做一些修改界面的操作
            val info = userInfo.username+"\n"+userInfo.descriptor
            tv.text = info
        } else {
            tv.text = "未登录(点击登录)"
        }
        Log.d(TAG, "本机登入状态，$loginState")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_accounts, container, false)

        tv = rootView.findViewById(R.id.ll_top)
        tv.setOnClickListener(this)
        rootView.userInfoSetting.setOnClickListener(this)
        val tv1 = rootView.findViewById<TextView>(R.id.tv_1)
        tv1.setOnClickListener(this)
        val buttonNotice = rootView.findViewById<Button>(R.id.buttonNotice)
        buttonNotice.setOnClickListener(this)

        return rootView
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.ll_top -> if (!loginState) startActivity(
                Intent(this.context, LoginActivity::class.java)
            )
            R.id.userInfoSetting -> if (loginState) { startActivity(
                Intent(this.context, UserSettingActivity::class.java)
            )} else toast("请先登录")
            R.id.tv_1 -> startActivity(
                Intent(this.context, CollectActivity::class.java))
            R.id.buttonNotice -> if (loginState) { startActivity(
                Intent(this.context, NoticeActivity::class.java)
            )} else toast("请先登录")
        }
    }

    companion object {
        const val TAG = "AccountsFragment"
    }
}