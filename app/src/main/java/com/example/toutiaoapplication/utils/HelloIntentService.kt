package com.example.toutiaoapplication.utils

import android.app.IntentService
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.toutiaoapplication.MainActivity
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.repo.ApiServers
import com.example.toutiaoapplication.repo.entities.ResponseNotice
import com.example.toutiaoapplication.ui.thread.ArticleActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A constructor is required, and must call the super [android.app.IntentService]
 * constructor with a name for the worker thread.
 */
class HelloIntentService : IntentService("HelloIntentService") {

    // FIXME 点击跳转但不会消失，手动右滑
    private fun sendNotification(title: String, content: String) {
        val nm = registerNotificationManager()

        val intent = Intent(this, ArticleActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("title", "title")
            putExtra("content", "content")
            putExtra("time", 1.toLong())
            putExtra("tid", 1)
        }
        val resultPendingIntent = PendingIntent.getActivity(this, 0,
            intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_collect_pink_24dp)
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setFullScreenIntent(resultPendingIntent,true)
            .setContentIntent(resultPendingIntent)
            .build()

        // startForeground(1, builder)
        nm.notify(1, builder)
    }

    private fun requestNotice() {
        ApiServers().getApiService().getNotice()
            .enqueue(object : Callback<ResponseNotice> {
                override fun onFailure(call: Call<ResponseNotice>, t: Throwable) {
                    Log.d(TAG, t.message.toString())
                }

                override fun onResponse(
                    call: Call<ResponseNotice>,
                    response: Response<ResponseNotice>
                ) {
                    if (response.isSuccessful) {
                        val set = loadNotiveId(MainActivity.instance)
                        // Log.d(TAG, set.toString())
                        // Log.d(TAG, response.body()?.data.toString())
                        val nid = response.body()!!.data[0].nid
                        val title = transUnixTime(response.body()!!.data[0].ntime)
                        val content = response.body()!!.data[0].ncont
                        val found = set?.find { nid.toString() in it }
                        // Log.d(TAG, found.toString())
                        if (found == null) {
                            Log.d(TAG, "收到新消息！")
                            sendNotification(title = title, content = content)
                            response.body()?.data?.let {
                                saveNoticeId(MainActivity.instance, it)
                            }
                        } else Log.d(TAG, "没有新消息")
                    }
                }
            })
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
            Log.d(TAG, "onHandleIntent")
            while (true) {
                requestNotice()
                Thread.sleep(10000)
            }

        } catch (e: InterruptedException) {
            // Restore interrupt status.
            Thread.currentThread().interrupt()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "消息停止服务")
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

    companion object {
        const val TAG = "HelloIntentService"
    }
}