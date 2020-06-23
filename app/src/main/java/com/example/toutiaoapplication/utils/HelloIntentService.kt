package com.example.toutiaoapplication.utils

import android.app.IntentService
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.ui.thread.AnotherThreadActivity
import okhttp3.internal.notify

/**
 * A constructor is required, and must call the super [android.app.IntentService]
 * constructor with a name for the worker thread.
 */
class HelloIntentService : IntentService("HelloIntentService") {

    // FIXME 点击跳转但不会消失，手动右滑
    private fun sendNotification() {
        val nm = registerNotificationManager()

        val intent = Intent(this, AnotherThreadActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("title", "title")
            putExtra("content", "content")
            putExtra("time", 1)
            putExtra("tid", 1)
        }
        val resultPendingIntent = PendingIntent.getActivity(this, 0,
            intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_collect_pink_24dp)
            .setContentTitle("textTitle")
            .setContentText("textContent")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setFullScreenIntent(resultPendingIntent,true)
            .setContentIntent(resultPendingIntent)
            .build()

        // startForeground(1, builder)
        nm.notify(1, builder)
    }
    /**
     * The IntentService calls this method from the default worker thread with
     * the intent that started the service. When this method returns, IntentService
     * stops the service, as appropriate.
     */
    override fun onHandleIntent(intent: Intent?) {
        // Normally we would do some work here, like download a file.
        // For our sample, we just sleep for 5 seconds.
        try {
            // Thread.sleep(10000)
            sendNotification()
            // while (true){
                // Thread.sleep(5000)
                // 不会弹窗
                // toast("runding")
                // Log.d(TAG, "running")
            // }

        } catch (e: InterruptedException) {
            // Restore interrupt status.
            Thread.currentThread().interrupt()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        toast("service starting")
        return super.onStartCommand(intent, flags, startId)
    }

    companion object {
        const val TAG = "HelloIntentService"
    }

    private fun registerNotificationManager(): NotificationManager {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        val channel = NotificationChannel(CHANNEL_ID, R.string.channel_name.toString(), NotificationManager.IMPORTANCE_DEFAULT)
        // Register the channel with the system
        val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

        return notificationManager
    }
}