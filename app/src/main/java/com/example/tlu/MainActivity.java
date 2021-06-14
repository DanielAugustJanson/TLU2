package com.example.tlu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;


public class MainActivity extends AppCompatActivity {

    //Creating a new instance of MapView Object.
    private MapView mapView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));

        setContentView(R.layout.activity_main);

        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {

                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

                        // Map is set up and the style has loaded. Now you can add data or make other map adjustments

                    }
                });
            }
        });

        //Gets the token that is required to be showcased in the beginning.
        //Mapbox.getInstance(this,getString(R.string.mapbox_access_token));

        //Leiab antud objektile ylesse selle ID mille main.xml-is ekraanile maarasime
        //mapView = (MapView) findViewById(R.id.mainScreenMap);
        //Pomst et kaart tootaks peame kirjutama ymber OpenGLi activitid. Vms. Ise ka eriti aru ei saanud
        //mapView.onCreate(savedInstanceState);
    }

    // Põmst peame iga activity funktsiooni alla kirjutama et Map teeks samaaegselt samu ajsu.


    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    public void OpenSettings(View V){
        // Teeb uue objekti open mis avab seadete lahtri
        Intent openS = new Intent(this,SettingsTab.class);
        startActivity(openS);
    }
}