package com.example.spider.savingstat;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/*** Created by spider on 3/7/16.*/
public class SettingFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
