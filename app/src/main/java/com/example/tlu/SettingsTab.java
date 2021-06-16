package com.example.tlu;


import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Locale;



public class SettingsTab extends AppCompatActivity {
    //name of the folder so to speak, and then attributes what we store in there.
    public static final String SHARED_PREFERENCES = "sharedPreferences";
    public static final String useElevator = "elevatorState";
    public static final String staffAccess = "staffState";

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch elevatorState;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch staffState;


    private boolean elevatorLoadSwitch;
    private boolean staffLoadSwitch;

    ImageButton en, est;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_tab);

        elevatorState = (Switch) findViewById(R.id.useElevatorSwitch);
        staffState = (Switch) findViewById(R.id.doorAccessSwitch);
        en = findViewById(R.id.engLng);
        est = findViewById(R.id.estLng);


        //Ootab kuni tuleb vajutus
        elevatorState.setOnClickListener(v -> SaveData());


        staffState.setOnClickListener(v -> SaveData());
        LoadData();
        UpdateViewData();

        en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLanguage("en");
                recreate();
            }
        });

        est.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLanguage("et");
                recreate();
            }
        });

    }
    //Saves switch state
    public void SaveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(useElevator,elevatorState.isChecked());
        editor.putBoolean(staffAccess,staffState.isChecked());

        editor.apply();
    }
    public void LoadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        elevatorLoadSwitch = sharedPreferences.getBoolean(useElevator,false);
        staffLoadSwitch = sharedPreferences.getBoolean(staffAccess,false);
    }
    public void UpdateViewData(){
        elevatorState.setChecked(elevatorLoadSwitch);
        staffState.setChecked(staffLoadSwitch);
    }
    public void toast(View view) {
        Toast.makeText(getApplicationContext(), "Work in progress", Toast.LENGTH_SHORT).show();
    }
    public void setLanguage(String lan){
        Locale locale = new Locale(lan);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("Language", lan);
        editor.apply();
    }

    public void loadLanguage(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String lang = prefs.getString("Language", "");
        setLanguage(lang);
    }

}


