package com.example.karimimo.testservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.example.karimimo.testservice.Retrofit.ApiClient;
import com.example.karimimo.testservice.Retrofit.ApiInterface;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendLocation extends Service {

    GPSTracker gps;
    Runnable run;
    private Handler handler1;

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not Yet Implemented");
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        try {
            handler1 = new Handler();
            run = new  Runnable() {

                @Override
                public void run() {

                    gps = new GPSTracker(getApplicationContext());
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    if (latitude != 0 && longitude != 0) {
                        ApiInterface apiServise = ApiClient.getClient().create(ApiInterface.class);

                        Call<ResponseBody> call = apiServise.Send(latitude, longitude);
                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                if (response.code() != 200) {
                                    Log.d("response ", response.message());
                                }
                                else if(response.code() == 200){
                                    Log.d("response ", response.message());
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call,
                                                  Throwable t) {
                                Log.d("fail ", t.getMessage());
                            }
                        });

                        //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_SHORT).show();
                        Log.d("Lat, Long ", String.valueOf(latitude) + ", " + String.valueOf(longitude));
                    }
                    handler1.postDelayed(run, 10000);
                }
            };
            handler1.post(run);

        } catch (Exception e) {
            Log.d("Err " , e.getMessage());
        }
        return START_STICKY;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Intent rsi = new Intent(getApplicationContext(), this.getClass());
        rsi.setPackage(getPackageName());
        startService(rsi);
        super.onTaskRemoved(rootIntent);
    }
}


