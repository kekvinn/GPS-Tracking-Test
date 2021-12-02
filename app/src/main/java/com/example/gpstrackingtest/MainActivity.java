package com.example.gpstrackingtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.google.android.gms.location.*;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int DEFAULT_UPDATE_INTERVAL = 30;
    public static final int FAST_UPDATE_INTERVAL = 1;
    private static final int PERMISSIONS_FINE_LOCATION = 99;
    TextView txtLat, txtLong, txtAlt, txtAcc, txtSpeed, txtSensor, txtUpdates, txtAddress;
    Switch swLocationUpdates, swGPS;

    // Location request is a config file for all settings related to FusedLocationProviderClient
    LocationRequest locationRequest;

    LocationCallback locationCallBack;

    // Google's API for location services. The majority of this app functions using this class.
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLat = findViewById(R.id.txtLat);
        txtLong = findViewById(R.id.txtLong);
        txtAlt = findViewById(R.id.txtAlt);
        txtAcc = findViewById(R.id.txtAcc);
        txtSpeed = findViewById(R.id.txtSpeed);
        txtSensor = findViewById(R.id.txtSensor);
        txtUpdates = findViewById(R.id.txtUpdates);
        txtAddress = findViewById(R.id.txtAddress);
        swLocationUpdates = findViewById(R.id.swLocationUpdates);
        swGPS = findViewById(R.id.swGPS);

        locationRequest = LocationRequest.create()
                .setInterval(1000 * DEFAULT_UPDATE_INTERVAL)
                .setFastestInterval(1000 * DEFAULT_UPDATE_INTERVAL)
                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                .setMaxWaitTime(1000);

        locationCallBack = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                updateUIValues(locationResult.getLastLocation());
            }
        };

        swGPS.setOnClickListener(v -> {
            if (swGPS.isChecked()) {
                locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                txtSensor.setText("Using GPS Sensors");
            } else {
                locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
                txtSensor.setText("Using Towers + WiFi");
            }
        });

        swLocationUpdates.setOnClickListener(v -> {
            if (swLocationUpdates.isChecked())
                startLocationUpdates();
            else
                stopLocationUpdates();
        });
        updateGPS();
    }


    private void startLocationUpdates() {
        txtUpdates.setText("Location is being tracked.");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallBack, null);
        updateGPS();
    }

    private void stopLocationUpdates() {
        txtUpdates.setText("Location is not being tracked.");
        txtLat.setText("Not tracking location");
        txtLong.setText("Not tracking location");
        txtSpeed.setText("Not tracking location");
        txtAddress.setText("Not tracking location");
        txtAcc.setText("Not tracking location");
        txtAlt.setText("Not tracking location");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode)
        {
            case PERMISSIONS_FINE_LOCATION:
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                updateGPS();
            }
            else
            {
                Toast.makeText(this, "This app requires permission to be granted in order to work properly.", Toast.LENGTH_SHORT).show();
                finish();
            }
            break;
        }
    }

    private void updateGPS() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
           fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
               @Override
               public void onSuccess(Location location) {
                   if (location != null)
                   {
                       updateUIValues(location);
                   }

               }
           });
        }
        else
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_FINE_LOCATION);
            }
        }
    }

    private void updateUIValues(Location location) {

        txtLat.setText(String.valueOf(location.getLatitude()));
        txtLong.setText(String.valueOf(location.getLongitude()));
        txtAcc.setText(String.valueOf(location.getAccuracy()));

        if (location.hasAltitude())
            txtAlt.setText(String.valueOf(location.getAltitude()));
        else
            txtAlt.setText("Not available");

        if (location.hasSpeed())
            txtSpeed.setText(String.valueOf(location.getSpeed()));
        else
            txtSpeed.setText("Not available");


        Geocoder geocoder = new Geocoder(MainActivity.this);

        try {
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            txtAddress.setText(addresses.get(0).getAddressLine(0));
        }
        catch (Exception e){
            txtAddress.setText("Unable to get street address.");
        }

    }
}