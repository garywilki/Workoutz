package com.example.workoutz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

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

/*            // Display data from the selected profile
            TextView profileTotalDays = findViewById(R.id.profileTotalDays);
            profileTotalDays.setText(String.valueOf(this.profile.totalDays));

            // Display data from the selected profile
            TextView profileTotalTime = findViewById(R.id.profileTotalTime);
            profileTotalTime.setText(String.valueOf(this.profile.totalTime));*/
        }
        else {
            // TO DO:
            // Handle case where the profile ID was not delivered
            Log.e("ProfileDashboard", "ProfileDashboard.onCreate() -> Profile ID failed to deliver: ID=" + profileID);
        }

        BarChart chart = findViewById(R.id.barchart);

        ArrayList NoOfEmp = new ArrayList();

        NoOfEmp.add(new BarEntry(32f, 0));
        NoOfEmp.add(new BarEntry(33f, 1));
        NoOfEmp.add(new BarEntry(36f, 2));
        NoOfEmp.add(new BarEntry(0f, 3));
        NoOfEmp.add(new BarEntry(24f, 4));
        NoOfEmp.add(new BarEntry(29f, 5));
        NoOfEmp.add(new BarEntry(12f, 6));
        NoOfEmp.add(new BarEntry(33f, 7));
        NoOfEmp.add(new BarEntry(40f, 8));
        NoOfEmp.add(new BarEntry(30f, 9));

        ArrayList year = new ArrayList();

        year.add("1");
        year.add("2");
        year.add("3");
        year.add("4");
        year.add("5");
        year.add("6");
        year.add("7");
        year.add("8");
        year.add("9");
        year.add("10");

        BarDataSet bardataset = new BarDataSet(NoOfEmp, "Total Workout Duration (minutes)");
        chart.animateY(2000);
        BarData data = new BarData(year, bardataset);
        //bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.setData(data);
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