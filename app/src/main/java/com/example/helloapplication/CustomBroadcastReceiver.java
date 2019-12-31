package com.example.helloapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class CustomBroadcastReceiver extends BroadcastReceiver {

    private final String TAG = "CustomReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        //Bundle bundle = this.getResultExtras(true);
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            String name = bundle.getString("name");

            Log.e(TAG, String.format("CustomReceiver received: name=%s", name));
        }
    }
}
