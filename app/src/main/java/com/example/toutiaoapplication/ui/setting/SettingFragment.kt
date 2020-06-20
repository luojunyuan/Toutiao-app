package com.example.toutiaoapplication.ui.setting

import android.app.AlertDialog
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.style.ClickableSpan
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.widget.Toast
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.toutiaoapplication.R
import com.example.toutiaoapplication.utils.getPortSP
import com.example.toutiaoapplication.utils.saveStringSP
import com.example.toutiaoapplication.utils.setPortSP
import com.example.toutiaoapplication.utils.toast

class SettingFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        val portAddress = findPreference<EditTextPreference>("port")
        portAddress?.text = context?.let { getPortSP(it) }
        portAddress?.setOnPreferenceChangeListener { _, newAddress ->
            toast("新的地址是 $newAddress")
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
    }

    companion object {
        const val TAG = "SettingFragment"
    }
}