package com.example.workoutz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ProfileDashboard extends AppCompatActivity {

    Profile profile;
    // Graph graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_dashboard);

        // Obtain the profile ID
        Intent intent = getIntent();
        int profileID = intent.getIntExtra(MainActivity.EXTRA_INT_PROFILEID, -1);

        if (profileID != -1) {
            // Acquire the selected profile
            this.profile = MainActivity.getProfile(profileID);

            // Display data from the selected profile
            TextView profileName = findViewById(R.id.profileName);
            profileName.setText(this.profile.name);
        }
        else {
            // TO DO:
            // Handle case where the profile ID was not delivered
        }
    }

    public void startButton(View view) {
        // TO DO:
        // Starts Workout activity
        Intent intent = new Intent(this, Workout.class);
        intent.putExtra(MainActivity.EXTRA_INT_PROFILEID, this.profile.id);
        startActivity(intent);
    }

    public void backButton(View view) {
        // TO DO:
        // Go to MainActivity
        // Do not save or modify the profileList
    }
}