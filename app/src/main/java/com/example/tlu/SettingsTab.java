package com.example.tlu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Switch;
import android.widget.ToggleButton;



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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_tab);

        elevatorState = (Switch) findViewById(R.id.useElevatorSwitch);
        staffState = (Switch) findViewById(R.id.doorAccessSwitch);


        //Ootab kuni tuleb vajutus
        elevatorState.setOnClickListener(v -> SaveData());


        staffState.setOnClickListener(v -> SaveData());
        LoadData();
        UpdateViewData();

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

}


