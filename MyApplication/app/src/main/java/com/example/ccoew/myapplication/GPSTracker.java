package com.example.ccoew.myapplication;

import android.Manifest;import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria; import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;import android.os.Bundle;
import android.support.v4.app.ActivityCompat;import android.util.Log;
public class GPSTracker implements LocationListener{
    private final Context mContext;
    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false; // flag for network status
    // flag for GPS status
    boolean canGetLocation = false;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
    LocationManager locationManager;
    Location location;
    String provider;
    double latitude;
    double longitude;
    public GPSTracker(Context context) {
        this.mContext = context;
        getLocation();}
    public void getLocation() {
        locationManager = (LocationManager)
                mContext.getSystemService(Context.LOCATION_SERVICE);
        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (ActivityCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {} // getting network status
        isNetworkEnabled =
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (!isGPSEnabled && !isNetworkEnabled) {
// no network provider is enabled
}
            else
            {
                if (isNetworkEnabled) {locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                        MIN_TIME_BW_UPDATES,MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    Log.d("Network", "Network");
                    if (locationManager != null) {
                        location =
                                locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();}
                    }
                    if (isGPSEnabled) {
                        if (location == null) {
                            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES,
                                    MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                            Log.d("GPS Enabled", "GPS Enabled");
                            if (locationManager != null) {
                                location =
                                        locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                                if (location != null) {
                                    latitude = location.getLatitude();
                                    longitude = location.getLongitude(); }
                            }
                        }
                    }
                }
            }
// getting network status
            Criteria criteria = new Criteria();
            provider = locationManager.getBestProvider(criteria, false);
        }
    public double getLatitude(){
        if(location != null){
            latitude = location.getLatitude();
        }
        return latitude;
    }
    public double getLongitude(){
        if(location != null){
            longitude = location.getLongitude();
        }
        return longitude;
    }
    public boolean canGetLocation() {
        return this.canGetLocation;
    }
    @Override
    public void onLocationChanged (Location location){
    }
    @Override
    public void onProviderDisabled (String provider){
    }
    @Override
    public void onProviderEnabled (String provider){
    }
    @Override
    public void onStatusChanged (String provider,int status, Bundle extras){
    }
}