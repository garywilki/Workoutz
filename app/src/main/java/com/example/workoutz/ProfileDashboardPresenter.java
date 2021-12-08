package com.example.workoutz;

import android.util.Log;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class ProfileDashboardPresenter {

    private final ProfileDashboardActivity activity;
    private final Profile profile;

    public ProfileDashboardPresenter(ProfileDashboardActivity activity, Profile profile) {
        // Initialize the activity variable
        this.activity = activity;
        this.profile = profile;

        this.profile.updateHistoryDates();

        Log.i("ProfileDashboard", "ProfileDashboard.onCreate() -> " + profile.id + " " + profile.name + " " + profile.reps + " " + profile.workIntervalSeconds + " " + profile.restIntervalSeconds + " & " + Profile.nextID);

        activity.displayTextStats();
        activity.displayBarChart();

    }
}
