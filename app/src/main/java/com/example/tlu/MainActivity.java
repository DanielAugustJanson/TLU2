package com.example.tlu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

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

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                mapboxMap.setStyle(new Style.Builder().fromUri("mapbox://styles/gustavjohannson/ckpwifbg0004a17qqya2umcf7"));



            }
        });


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

    public void toast(View view) {
        Toast.makeText(getApplicationContext(),"Work in progress",Toast.LENGTH_SHORT).show();
    }


    public void esimene(View view) {
        Toast.makeText(getApplicationContext(),"You are viewing the first floor",Toast.LENGTH_SHORT).show();
    }

    public void play(View view) {
        ImageView arrowdown= findViewById(R.id.arrowDown);
        arrowdown.setVisibility(View.VISIBLE);
        ImageView arrowup= findViewById(R.id.arrowUp);
        arrowup.setVisibility(View.VISIBLE);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.up_down);
        Animation animation2= AnimationUtils.loadAnimation(this,R.anim.down_up);

        arrowup.startAnimation(animation);
        arrowdown.startAnimation(animation2);

    }

    public void endToStart(View view) {

        AutoCompleteTextView start = findViewById(R.id.StartPoint);
        AutoCompleteTextView end = findViewById(R.id.EndPoint);
        String getStart = start.getText().toString();
        String getEnd = end.getText().toString();
        String holder=getStart;
        start.setText(getEnd);
        end.setText(holder);



    }
}