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
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
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

    private FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        fab = (FloatingActionButton) findViewById(R.id.fab);




        sonido = MediaPlayer.create(this,R.raw.seki);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sonido.start();

                //aqui debe ir un ramo de condiciones para los sonidos similar al de las latitudes

            }
        });



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

        ///MARCADORES LINEA B

        // Add a marker in Casa abuelos and move the camera
        LatLng casaAbuelos = new LatLng(19.476611, -99.071680);
        gMap.addMarker(new MarkerOptions().position(casaAbuelos).title("Casa abuelos").draggable(true));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(casaAbuelos, 12), 3000, null);

        // Add a marker in Mbuenavista and move the camera
        LatLng Mbuenavista = new LatLng(19.446629, -99.152790);
        gMap.addMarker(new MarkerOptions().position(Mbuenavista).title("Metro Buenavista").draggable(true));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Mbuenavista, 12), 3000, null);

        // Add a marker in Mguerrero and move the camera
        LatLng Mguerrero = new LatLng(19.445492, -99.146703);
        gMap.addMarker(new MarkerOptions().position(Mguerrero).title("Metro Guerrero").draggable(true));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Mguerrero, 12), 3000, null);

        // Add a marker in MgaribaldiLagunilla and move the camera
        LatLng MgaribaldiLagunilla = new LatLng(19.444210, -99.139009);
        gMap.addMarker(new MarkerOptions().position(MgaribaldiLagunilla).title("Metro Garibaldi/Lagunilla").draggable(true));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(MgaribaldiLagunilla, 12), 3000, null);

        //////analizar coordenadas

        // Add a marker in Mlagunilla and move the camera
        LatLng Mlagunilla = new LatLng(19.443341, -99.131812);
        gMap.addMarker(new MarkerOptions().position(Mlagunilla).title("Metro Lagunilla").draggable(true));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Mlagunilla, 12), 3000, null);

        // Add a marker in Mtepito and move the camera
        LatLng Mtepito = new LatLng(119.442825, -99.124096);
        gMap.addMarker(new MarkerOptions().position(Mtepito).title("Metro Tepito").draggable(true));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Mtepito, 12), 3000, null);

        // Add a marker in Mmorelos and move the camera
        LatLng Mmorelos = new LatLng(19.439688, -99.118142);
        gMap.addMarker(new MarkerOptions().position(Mmorelos).title("Metro Morelos").draggable(true));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Mmorelos, 12), 3000, null);

        // Add a marker in MsanLazaro and move the camera
        LatLng MsanLazaro = new LatLng(19.431983, -99.113726);
        gMap.addMarker(new MarkerOptions().position(MsanLazaro).title("Metro San Lázaro").draggable(true));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(MsanLazaro, 12), 3000, null);

        // Add a marker in MflorresM and move the camera
        LatLng MflorresM = new LatLng(19.436588, -99.103651);
        gMap.addMarker(new MarkerOptions().position(MflorresM).title("Metro Flores Magón").draggable(true));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(MflorresM, 12), 3000, null);

        // Add a marker in MromeroR and move the camera
        LatLng MromeroR = new LatLng(19.440777, -99.094812);
        gMap.addMarker(new MarkerOptions().position(MromeroR).title("Metro Romero Rubio").draggable(true));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(MromeroR, 12), 3000, null);

        // Add a marker in Moceania and move the camera
        LatLng Moceania = new LatLng(19.445786, -99.087129);
        gMap.addMarker(new MarkerOptions().position(Moceania).title("Metro Oceanía").draggable(true));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Moceania, 12), 3000, null);

        // Add a marker in MdepOceania and move the camera
        LatLng MdepOceania = new LatLng(19.451018, -99.079304);
        gMap.addMarker(new MarkerOptions().position(MdepOceania).title("Metro Deportivo Oceanía").draggable(true));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(MdepOceania, 12), 3000, null);

        // Add a marker in MbosqArag and move the camera
        LatLng MbosqArag = new LatLng(19.458126, -99.069286);
        gMap.addMarker(new MarkerOptions().position(MbosqArag).title("Metro Bosque de Aragón").draggable(true));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(MbosqArag, 12), 3000, null);

        // Add a marker in MvillaArag and move the camera
        LatLng MvillaArag = new LatLng(19.461669, -99.061313);
        gMap.addMarker(new MarkerOptions().position(MvillaArag).title("Metro Villa de Aragón").draggable(true));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(MvillaArag, 12), 3000, null);

        // Add a marker in Mneza move the camera
        LatLng Mneza = new LatLng(19.472649, -99.054720);
        gMap.addMarker(new MarkerOptions().position(Mneza).title("Metro Villa de Aragón").draggable(true));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Mneza, 12), 3000, null);

        // Add a marker in Mimpu move the camera
        LatLng Mimpu = new LatLng(19.485583, -99.049031);
        gMap.addMarker(new MarkerOptions().position(Mimpu).title("Metro Impulsora").draggable(true));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Mimpu, 12), 3000, null);

        // Add a marker in MrioRem move the camera
        LatLng MrioRem = new LatLng(19.490648, -99.046825);
        gMap.addMarker(new MarkerOptions().position(MrioRem).title("Metro Rio de los Remedios").draggable(true));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(MrioRem, 12), 3000, null);

        // Add a marker in Mmuzq move the camera
        LatLng Mmuzq = new LatLng(19.501336, -99.042148);
        gMap.addMarker(new MarkerOptions().position(Mmuzq).title("Metro Múzquiz").draggable(true));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Mmuzq, 12), 3000, null);

        // Add a marker in Mecat move the camera
        LatLng Mecat = new LatLng(19.514855, -99.036208);
        gMap.addMarker(new MarkerOptions().position(Mecat).title("Metro Ecatepec").draggable(true));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Mecat, 12), 3000, null);

        // Add a marker in Moli move the camera
        LatLng Moli = new LatLng(19.521551, -99.033294);
        gMap.addMarker(new MarkerOptions().position(Moli).title("Metro Olímpica").draggable(true));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Moli, 12), 3000, null);

        // Add a marker in MplazaArag move the camera
        LatLng MplazaArag = new LatLng(19.528451, -99.030144);
        gMap.addMarker(new MarkerOptions().position(MplazaArag).title("Metro Plaza Aragón").draggable(true));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(MplazaArag, 12), 3000, null);

        // Add a marker in McdAzte move the camera
        LatLng McdAzte = new LatLng(19.534699, -99.027391);
        gMap.addMarker(new MarkerOptions().position(McdAzte).title("Metro CD Aztéca").draggable(true));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(McdAzte, 12), 3000, null);



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


        ////variable de latitud al textview
        TextView latituD = (TextView) findViewById(R.id.latitud);

        latituD.setText(" Latitud: " + location.getLatitude());


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
