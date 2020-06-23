package com.example.toutiaoapplication.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.toutiaoapplication.MainActivity

private class ScreenBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(
        context: Context,
        intent: Intent
    ) {
        // 只有从一个状态变为另一个状态时才会触发
        when (intent.action) {
            // 开屏
            Intent.ACTION_SCREEN_ON -> {
                Log.d(MainActivity.TAG, "当前亮屏状态")
            }
            // 锁屏
            Intent.ACTION_SCREEN_OFF -> {
                Log.d(MainActivity.TAG, "当前锁屏状态")
            }
            // 解锁
            Intent.ACTION_USER_PRESENT -> {
                Log.d(MainActivity.TAG, "当前解锁状态")
                Intent(MainActivity.instance, HelloIntentService::class.java).also { serviceIntent ->
                    MainActivity.instance.startService(serviceIntent)
                    // error
                    // startForegroundService(intent)
                }
            }
        }
    }
}