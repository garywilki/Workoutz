package com.example.workoutz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileDashboard extends AppCompatActivity {

    // Graph graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_dashboard);

        // Display data from the selected profile
        Intent intent = getIntent();
        int profileID = intent.getIntExtra(MainActivity.EXTRA_INT_PROFILEID, -1);
        if (profileID != -1) {
            TextView profileName = findViewById(R.id.profileName);
            profileName.setText(MainActivity.profileList.get(profileID).name);
        }
    }

    public void startButton() {
        // TO DO:
        // Starts Workout activity
    }

    public void backButton() {
        // TO DO:
        // Go to MainActivity
        // Do not save or modify the profileList
    }
}