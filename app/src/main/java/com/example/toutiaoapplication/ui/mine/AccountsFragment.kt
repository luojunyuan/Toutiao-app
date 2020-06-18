package com.example.toutiaoapplication.ui.mine

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.ui.login.LoginActivity

class AccountsFragment : Fragment(), View.OnClickListener{

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
            R.id.ll_top -> startActivity(Intent(this.context, LoginActivity::class.java))
        }
    }

    companion object {
        const val TAG = "AccountsFragment"
    }
}