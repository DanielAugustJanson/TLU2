package com.example.tlu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tlu.base.*;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.RasterLayer;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    //Creating a new instance of MapView Object.
    private MapView mapView;
    public static AppiDatabase db;
    private final ThreadPoolExecutor appiExecute = new ThreadPoolExecutor(2, 4, 500, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    public static List<Room> forPoints;
    public static List<Room> roomsForLookUp;
    public static int floorDisplayed = 1;
    public static int roomIDForDestination;
    public static String searchTerm;

    AutoCompleteTextView autoCompleteTextView;
    Button calendar;

    public static List<NavPoints> destinationHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));


        db = androidx.room.Room.databaseBuilder(getApplicationContext(),
                AppiDatabase.class, "appi-data.db").createFromAsset("database/appi.db").fallbackToDestructiveMigration().build();

        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name2));

        calendar = findViewById(R.id.buttonCalender);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast(v);
            }
        });

        appiExecute.execute(new GetRoomPoints());
        //appiExecute.execute(new LookForRoomByCode());

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                mapboxMap.setStyle(RasterLayer( Style.Builder().fromUri("mapbox://styles/gustavjohannson/ckpzb9eop05mq18qviptlbm5d"), new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        
                        style.addLayer(RasterLayer("raster-layer","background");

                        // Custom map style has been loaded and map is now ready


                    }
                });



            }
        });

        autoCompleteTextView = findViewById(R.id.StartPoint);


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
        String str = getLocaleString(R.string.toast1);
        Toast.makeText(getApplicationContext(), str,Toast.LENGTH_SHORT).show();
    }


    public void esimene(View view) {
        String str = getLocaleString(R.string.toast_second);
        Toast.makeText(getApplicationContext(), str ,Toast.LENGTH_SHORT).show();
    }

    public void play(View view) {
        ImageView arrowdown= findViewById(R.id.arrowDown);
        arrowdown.setVisibility(View.VISIBLE);
        ImageView arrowup= findViewById(R.id.arrowUp);
        arrowup.setVisibility(View.VISIBLE);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.up_down);
        Animation animation2= AnimationUtils.loadAnimation(this,R.anim.down_up);

        arrowup.startAnimation(animation2);
        arrowdown.startAnimation(animation);

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
    public String getLocaleString(int id){
        Locale current = getResources().getConfiguration().locale;
        Configuration conf = getResources().getConfiguration();
        conf.locale = new Locale(current.getLanguage());
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Resources resources = new Resources(getAssets(), metrics, conf);
        return resources.getString(id);
    }

}