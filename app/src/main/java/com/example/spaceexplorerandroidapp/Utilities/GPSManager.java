package com.example.spaceexplorerandroidapp.Utilities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class GPSManager {


    private LocationManager locationManager;

    private Context context;
    private double lat = 0;
    private double lon = 0;


    public GPSManager(Context context) {
        this.context = context;
    }

    public void start(){
        locationManager =(LocationManager) context.getSystemService(context.LOCATION_SERVICE);
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 1, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                setLat(location.getLatitude());
                setLon(location.getLongitude());
            }
        });


    }
    public void stop(){
        if(locationManager != null){
            locationManager = null;
        }
    }

    public double getLat() {
        return lat;
    }

    public GPSManager setLat(double lat) {
        this.lat = lat;
        return this;
    }

    public double getLon() {
        return lon;
    }

    public GPSManager setLon(double lon) {
        this.lon = lon;
        return this;
    }
}
