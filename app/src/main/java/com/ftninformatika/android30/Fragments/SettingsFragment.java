package com.ftninformatika.android30.Fragments;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.ftninformatika.android30.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preference);
    }
}
