package com.example.karimimo.testservice;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.widget.Toast;

public class SendLocation extends Service {

    GPSTracker gps;

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not Yet Implemented");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        new CountDownTimer(10000,1000) {

            @SuppressLint({"DefaultLocale","SetTextI18n"})
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                gps = new GPSTracker(getApplicationContext());
                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();
                Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_SHORT).show();
            }
        }.start();
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {

        return START_STICKY;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Intent rsi = new Intent(getApplicationContext(), this.getClass());
        rsi.setPackage(getPackageName());
        startService(rsi);
    }
}
