
package com.example.ra3y;

import static com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
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
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class Mapss extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {
    boolean perminted;
    GoogleMap map;
    FloatingActionButton fab;
    private FusedLocationProviderClient mlocation;
    String longitude, latitude;
    private final CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        checkpermission();
        fab=findViewById(R.id.fab);
        init();
        mlocation=new FusedLocationProviderClient(this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getloc();

            }
        });
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
                        Log.d("loc", String.valueOf(loc));
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
        map.addMarker(new MarkerOptions()
                .position(ll)
        );

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
        Gotoprofile.putExtra("long", longitude);
        Gotoprofile.putExtra("lat", latitude);
        startActivity(Gotoprofile);
        Log.d("LOCATION", longitude );
        Log.d("LOCATION", latitude);
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
}

