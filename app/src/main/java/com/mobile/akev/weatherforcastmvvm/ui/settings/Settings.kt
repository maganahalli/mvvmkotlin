package com.mobile.akev.weatherforcastmvvm.ui.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import com.mobile.akev.weatherforcastmvvm.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        updateToolBar("Settings", "")
    }

    private fun updateToolBar(title: String, subTitle: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = title
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = subTitle
    }
}