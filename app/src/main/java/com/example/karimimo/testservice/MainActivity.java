package com.example.karimimo.testservice;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvLat, tvLong;
    Button btnRefresh;
    //static final int REQUEST_LOCATION = 1;
    LocationManager  locationManager;
    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvLat = (TextView)findViewById(R.id.tvLati);
        tvLong = (TextView)findViewById(R.id.tvLong);
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    public void btnonClickListener(View v){
        //getLocation();
        gps = new GPSTracker(MainActivity.this);
        if(gps.canGetLocation()){
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
            tvLat.setText(String.valueOf("Lat : "+latitude));
            tvLong.setText(String.valueOf("Long : "+longitude));
        }else{

            gps.showSettingsAlert();
        }
    }

}
