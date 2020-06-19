package com.example.toutiaoapplication.ui.setting

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.toutiaoapplication.R

class SettingFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

}