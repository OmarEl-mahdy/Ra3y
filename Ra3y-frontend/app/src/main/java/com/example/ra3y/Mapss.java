
package com.example.ra3y;

import static com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

public class Mapss extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {
    boolean perminted;
    GoogleMap map;
    FloatingActionButton fab;
    Button proceed;
    private FusedLocationProviderClient mlocation;
<<<<<<< Updated upstream
<<<<<<< HEAD
    String longitude, latitude, addr;
=======
    String longitude, latitude;
>>>>>>> c5cb3a96efaabfd40008aa7c236b98a45b89b689
=======
    String longitude, latitude, addr;
>>>>>>> Stashed changes
    private final CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        checkpermission();
        fab=findViewById(R.id.fab);
        init();
        mlocation=new FusedLocationProviderClient(this);
        proceed = findViewById(R.id.proceed);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getloc();
            }
        });
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loading = new Intent(getApplicationContext(), com.example.ra3y.loading.class);
                loading.putExtra("address", addr);
                loading.putExtra("longitude", longitude);
                loading.putExtra("latitude", latitude);
                startActivity(loading);
            }
        });
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loading = new Intent(getApplicationContext(), com.example.ra3y.loading.class);
                loading.putExtra("address", addr);
                loading.putExtra("longitude", longitude);
                loading.putExtra("latitude", latitude);
                startActivity(loading);
            }
        });
    }
    //#############################
    private class API_handler extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
    //#############################
    private class API_handler extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
    private void init()
    {
        if(perminted)
        {
            SupportMapFragment supp=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
            supp.getMapAsync((OnMapReadyCallback) this);

        }
    }
    @SuppressLint("MissingPermission")
    private void getloc()
    {

        mlocation.getCurrentLocation(PRIORITY_HIGH_ACCURACY,cancellationTokenSource.getToken()).addOnCompleteListener(task ->
                {
                    if(task.isSuccessful()){
                        Location loc=task.getResult();
                        gotoLocation(loc.getLatitude(),loc.getLongitude());
                        latitude = String.valueOf(loc.getLatitude());
                        longitude = String.valueOf(loc.getLongitude());


                    }
                }
        );
    }

    private void gotoLocation(double latitude, double longitude) {

        LatLng ll=new LatLng(latitude,longitude);
        CameraUpdate cam= CameraUpdateFactory.newLatLngZoom(ll,15);

        map.moveCamera(cam);
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
<<<<<<< Updated upstream
<<<<<<< HEAD
=======
>>>>>>> Stashed changes
        MarkerOptions markerOptions  = new MarkerOptions().position(ll);
        map.addMarker(markerOptions);
        addr= getAddress(this, latitude,longitude);
        Log.d("Title", addr);
        //
//        markerOptions.getTitle();
//        Log.d("Title", markerOptions.getTitle());
<<<<<<< Updated upstream
=======
        map.addMarker(new MarkerOptions()
                .position(ll)
        );
>>>>>>> c5cb3a96efaabfd40008aa7c236b98a45b89b689
=======
>>>>>>> Stashed changes

    }
    public String getAddress(Context context, double lat, double lng) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);

            String add = obj.getAddressLine(0);

            return add;
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    private void checkpermission() {
        Dexter.withContext(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                perminted=true;
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Intent intent=new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri= Uri.fromParts("package",getPackageName(),"");
                intent.setData(uri);
                startActivity(intent);
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map=googleMap;
        //map.setMyLocationEnabled(true);


    }

    @Override
    public void onBackPressed() {
        Intent Gotoprofile = new Intent(getApplicationContext(), sitterProfile.class);
<<<<<<< Updated upstream
<<<<<<< HEAD
//        Gotoprofile.putExtra("long", longitude);
//        Gotoprofile.putExtra("lat", latitude);
//        Gotoprofile.putExtra("addr", addr);
        startActivity(Gotoprofile);
//        Log.d("LOCATION", longitude );
//        Log.d("LOCATION", latitude);
=======
        Gotoprofile.putExtra("long", longitude);
        Gotoprofile.putExtra("lat", latitude);
        startActivity(Gotoprofile);
        Log.d("LOCATION", longitude );
        Log.d("LOCATION", latitude);
>>>>>>> c5cb3a96efaabfd40008aa7c236b98a45b89b689
=======
//        Gotoprofile.putExtra("long", longitude);
//        Gotoprofile.putExtra("lat", latitude);
//        Gotoprofile.putExtra("addr", addr);
        startActivity(Gotoprofile);
//        Log.d("LOCATION", longitude );
//        Log.d("LOCATION", latitude);
>>>>>>> Stashed changes
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
<<<<<<< HEAD
}
=======
}

>>>>>>> c5cb3a96efaabfd40008aa7c236b98a45b89b689
