package com.example.toutiaoapplication.ui.register
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.utils.toast
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register.setOnClickListener{
            if (password_1.text.trim().toString() == password_2.text.trim().toString()) {
                // 发送信息到api
                toast("注册陈公")
            } else toast("两次输入密码不一致")
        }
    }
}