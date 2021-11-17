package com.example.workoutz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        int profileID = intent.getIntExtra(MainModel.EXTRA_INT_PROFILEID, Profile.NULL_ID);

        if (profileID != Profile.NULL_ID) {
            // Acquire the selected profile
            this.profile = MainModel.getProfile(profileID);

            Log.i("ProfileDashboard", "ProfileDashboard.onCreate() -> " + profile.id + " " + profile.name + " " + profile.reps + " " + profile.workIntervalSeconds + " " + profile.restIntervalSeconds + " & " + Profile.nextID);

            // Display data from the selected profile
            TextView profileName = findViewById(R.id.profileName);
            profileName.setText(this.profile.name);

            // Display data from the selected profile
            TextView profileReps = findViewById(R.id.profileReps);
            profileReps.setText(String.valueOf(this.profile.reps));

            // Display data from the selected profile
            TextView profileWorkInterval = findViewById(R.id.profileWorkInterval);
            profileWorkInterval.setText(String.valueOf(this.profile.workIntervalSeconds));

            // Display data from the selected profile
            TextView profileRestInterval = findViewById(R.id.profileRestInterval);
            profileRestInterval.setText(String.valueOf(this.profile.restIntervalSeconds));

            // Display data from the selected profile
            TextView profileTotalDays = findViewById(R.id.profileTotalDays);
            profileTotalDays.setText(String.valueOf(this.profile.totalDays));

            // Display data from the selected profile
            TextView profileTotalTime = findViewById(R.id.profileTotalTime);
            profileTotalTime.setText(String.valueOf(this.profile.totalTime));
        }
        else {
            // TO DO:
            // Handle case where the profile ID was not delivered
            Log.e("ProfileDashboard", "ProfileDashboard.onCreate() -> Profile ID failed to deliver: ID=" + profileID);
        }
    }

    public void startButton(View view) {
        // TO DO:
        // Starts Workout activity
        Intent intent = new Intent(this, Workout.class);
        intent.putExtra(MainModel.EXTRA_INT_PROFILEID, this.profile.id);
        startActivity(intent);
    }

    public void backButton(View view) {
        // TO DO:
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}