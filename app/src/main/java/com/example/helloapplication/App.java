package com.example.helloapplication;

import android.app.Application;
import android.util.Log;

/**
 * Created by sunshow.
 */
public class App extends Application {

    private final static String TAG = "APP";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "App onCreate");
    }

}
