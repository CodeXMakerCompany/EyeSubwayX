package com.example.eyesubway;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Tracking extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap gMap;
    //var localización
    private LocationManager locationManager;
    private Location currentLocation;
    //var sonido
    MediaPlayer sonido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        sonido = MediaPlayer.create(this,R.raw.seki);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    private boolean GPSenable() {
        try {
            int gpsSignal = Settings.Secure.getInt(getContentResolver(), Settings.Secure.LOCATION_MODE);
            if (gpsSignal == 0) {
                //no tenemos señal de gps equiosde
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);

                return false;
            } else {
                return true;
            }

        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return false;
        }


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        GPSenable();
        locationManager = (LocationManager) getBaseContext().getSystemService(LOCATION_SERVICE);





        // Add a marker in Sydney and move the camera
        LatLng casaAbuelos = new LatLng(19.476611, -99.071680);
        gMap.addMarker(new MarkerOptions().position(casaAbuelos).title("Casa abuelos").draggable(true));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(casaAbuelos, 15), 3000, null);



        if (ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        gMap.setMyLocationEnabled(true);

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 0, this);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);


        gMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                Toast.makeText(getBaseContext(), "Executing", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }


    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(getBaseContext(), "Address Updated -> "+ location.getProvider() + " GPS " + location.getLatitude(), Toast.LENGTH_LONG).show();

        //LatLng UbicacionEx = new LatLng(location.getLatitude(),location.getLongitude());

        //Toast.makeText(getBaseContext(), "Ubicacion " + UbicacionEx, Toast.LENGTH_SHORT).show();

        //LatLng destino = new LatLng(19.476611, -99.071680);


    if ( location.getLatitude() > 19.47676600  ){

        Toast.makeText(getBaseContext(), " Llego a su destino " + location.getLatitude(), Toast.LENGTH_LONG).show();
        sonido.start();





    }else{

        Toast.makeText(getBaseContext(), " Movimiento estático " + location.getLatitude(), Toast.LENGTH_LONG).show();
        //Toast.makeText(getBaseContext(), "accuracy"  + location.getAccuracy(), Toast.LENGTH_LONG).show();
        //Toast.makeText(getBaseContext(), "bearing"  + location.getBearing(), Toast.LENGTH_LONG).show();
        //Toast.makeText(getBaseContext(), "extras"  + location.getExtras(), Toast.LENGTH_LONG).show();
        //Toast.makeText(getBaseContext(), "time"  + location.getTime(), Toast.LENGTH_LONG).show();
        //Toast.makeText(getBaseContext(), "longitude"  + location.getLongitude(), Toast.LENGTH_LONG).show();

    }

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }




}
