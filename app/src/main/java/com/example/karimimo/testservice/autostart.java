package com.example.karimimo.testservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class autostart extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent arg) {
        Intent intent = new Intent(context,SendLocation.class);
        context.startService(intent);
        Log.i("Autostart", "started");
    }
}
