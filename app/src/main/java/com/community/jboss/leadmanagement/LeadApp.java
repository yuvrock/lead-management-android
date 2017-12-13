package com.community.jboss.leadmanagement;

import com.orm.SugarApp;

import timber.log.Timber;

public class LeadApp extends SugarApp {
    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
