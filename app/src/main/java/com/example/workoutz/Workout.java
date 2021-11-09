package com.example.workoutz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Workout extends AppCompatActivity {

    // Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
    }

    public void togglePauseButton() {
        // TO DO:
        // Pauses/Unpauses the workout timer
    }

    public void stopButton() {
        // Ends workout
        // Saving profile data
        // Returns to ProfileDashboard activity
    }
}