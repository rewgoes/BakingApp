package com.wolfgoes.bakingapp;

import android.app.Application;

import timber.log.Timber;

public class BakingApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
