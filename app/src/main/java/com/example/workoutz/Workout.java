package com.example.workoutz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class Workout extends AppCompatActivity {

    Profile profile;
    // Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
    }

    public void togglePauseButton(View view) {
        // TO DO:
        // Pauses/Unpauses the workout timer
    }

    public void stopButton(View view) {
        // Ends workout
        // Saving profile data
        // Returns to ProfileDashboard activity
    }
}