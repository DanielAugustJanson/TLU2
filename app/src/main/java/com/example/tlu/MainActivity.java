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
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.Layer;
import com.mapbox.mapboxsdk.style.layers.Property;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.rasterOpacity;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.visibility;


public class MainActivity extends AppCompatActivity {

    //Creating a new instance of MapView Object.
    private MapView mapView;
    private MapboxMap mapboxMap;
    public static AppiDatabase db;
    private final ThreadPoolExecutor appiExecute = new ThreadPoolExecutor(2, 4, 500, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    public static List<Room> forPoints;
    public static List<Room> roomsForLookUp;
    public static int floorDisplayed = 1;
    public static int roomIDForDestination;
    public static String searchTerm;
    public static List<NavPoints> destinationHolder;

    public Layer astra1K;
    public Layer astra1AK;
    public Layer astra2K;
    public Button buttonA1A;
    public Button buttonA1;
    public Button buttonA2;


    AutoCompleteTextView autoCompleteTextView;
    Button calendar;

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
                MainActivity.this.mapboxMap = mapboxMap;



                mapboxMap.setStyle(new Style.Builder().fromUri("mapbox://styles/gustavjohannson/ckpzb9eop05mq18qviptlbm5d"), new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

                        Layer background = style.getLayer("background");
                        Layer groundFloor = style.getLayer("Base");
                        //
                        Layer astra1K = style.getLayer("Astra-1K");
                        Layer astra1AK = style.getLayer("Astra-1AK");
                        Layer astra2K = style.getLayer("Astra-2K");


                        //Seadistame esialgse kaamera positsiooni - Suurt vahet pole, niigi ei tööta.
                        CameraPosition startingCameraPosition = new CameraPosition.Builder()
                                .target(new LatLng(0.3177,-0.2487))
                                .zoom(10)
                                .build();

                        mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(startingCameraPosition),100);

                        Button buttonA1A = findViewById(R.id.floor1a);
                        Button buttonA1 = findViewById(R.id.floor1);
                        Button buttonA2 = findViewById(R.id.floor2);

                        buttonA1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ChangeLayer("A1");

                            }
                        });
                        buttonA1A.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ChangeLayer("A1A");

                            }
                        });
                        buttonA2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ChangeLayer("A2");

                            }
                        });
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
    public void ChangeLayer(String floornumber){
        mapboxMap.getStyle(new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull @NotNull Style style) {
                Layer astra1K = style.getLayer("Astra-1K");
                Layer astra1AK = style.getLayer("Astra-1AK");
                Layer astra2K = style.getLayer("Astra-2K");

                switch (floornumber){
                    case "A1":
                        astra1K.setProperties(visibility(Property.VISIBLE));
                        astra1AK.setProperties(visibility(Property.NONE));
                        astra2K.setProperties(visibility(Property.NONE));
                        break;
                    case "A1A":
                        astra1K.setProperties(visibility(Property.NONE));
                        astra1AK.setProperties(visibility(Property.VISIBLE));
                        astra2K.setProperties(visibility(Property.NONE));
                        break;
                    case "A2":
                        astra1K.setProperties(visibility(Property.NONE));
                        astra1AK.setProperties(visibility(Property.NONE));
                        astra2K.setProperties(visibility(Property.VISIBLE));
                }

            }
        });
    }
    //public void SetMatkers(floornumber){

}