package com.example.toutiaoapplication.ui.setting

import android.annotation.TargetApi
import android.app.ActivityManager
import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.repo.ApiServers
import com.example.toutiaoapplication.repo.entities.CheckResponse
import com.example.toutiaoapplication.utils.getPortSP
import com.example.toutiaoapplication.utils.loadSavedUserInfo
import com.example.toutiaoapplication.utils.setPortSP
import com.example.toutiaoapplication.utils.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SettingFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        val portAddress = findPreference<EditTextPreference>("port")
        portAddress?.text = context?.let { getPortSP(it) }
        portAddress?.setOnPreferenceChangeListener { _, newAddress ->
            toast("新的地址是 $newAddress, 请重启应用程序以生效！")
            context?.let { setPortSP(it, newAddress as String) }
            true
        }

        portAddress?.let { Log.d(TAG, it.text )}

        val about = findPreference<Preference>("about")
        about?.setOnPreferenceClickListener { _ ->
            Log.d(TAG, "asd")
            AlertDialog.Builder(context)
                .setTitle("郑丰华JAVA实验前端练手项目")
                .setMessage("项目地址：https://gitee.com/transgithub/Toutiao-app")
                .setPositiveButton("了解") { _, _ -> }
                .show()
            true
        }

        val exit = findPreference<Preference>("exit")
        exit?.setOnPreferenceClickListener { _ ->
            Log.d(TAG, "exit app")
            exitAPP()
            // android.os.Process.killProcess(android.os.Process.myPid())
            true
        }

        val check = findPreference<Preference>("check")
        check?.setOnPreferenceClickListener { _ ->
            ApiServers().getApiService().checkLogin()
                .enqueue(object : Callback<CheckResponse> {
                    override fun onFailure(call: Call<CheckResponse>, t: Throwable) {
                        Log.d(TAG, t.toString())
                        Log.d(TAG, t.localizedMessage!!)
                        toast(t.message.toString())
                    }

                    override fun onResponse(
                        call: Call<CheckResponse>,
                        response: Response<CheckResponse>
                    ) {
                        Log.d(TAG, "查看cookie情况： ${loadSavedUserInfo(requireContext()).cookie}")
                        toast(loadSavedUserInfo(requireContext()).cookie)
                        if (response.isSuccessful) {
                            Log.d(TAG, response.toString())
                            Log.d(TAG, response.body().toString())
                            toast(response.body().toString())
                        }
                        else{
                            Log.d("NetWork",response.message())
                        }
                    }

                })
            true
        }
    }

    // 销毁任务栈
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun exitAPP() {
        val activityManager = requireContext().applicationContext
            .getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val appTaskList = activityManager.appTasks
        for (appTask in appTaskList) {
            appTask.finishAndRemoveTask()
        }
    }

    companion object {
        const val TAG = "SettingFragment"
    }
}