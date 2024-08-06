package com.example.orienttion;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import java.util.prefs.PreferenceChangeEvent;

public class MainActivity extends AppCompatActivity {
    private boolean isPortrait;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        isPortrait = preferences.getBoolean("orientation_preference", true);

        setOrientation(isPortrait);

        Button buttonChangedOrientation = findViewById(R.id.buttonChangeOrientation);
        Button buttonMoveToNextActivity = findViewById(R.id.buttonMoveToNextActivity);

        buttonChangedOrientation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isPortrait = !isPortrait;
                saveOrientationPreference(isPortrait);
                setOrientation(isPortrait);

            }
        });

        buttonMoveToNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Secondactivity.class);
                intent.putExtra("orientation_preference", isPortrait);
                startActivity(intent);
            }
        });
    }

    private void setOrientation(boolean isPortrait) {
        if (isPortrait) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
        }
    }

        private void saveOrientationPreference(boolean isPortrait) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("orientation_preference", isPortrait);
            editor.apply();
        }


}


