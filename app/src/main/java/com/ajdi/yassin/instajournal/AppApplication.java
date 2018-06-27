package com.ajdi.yassin.instajournal;

import android.app.Application;

import timber.log.Timber;

public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
