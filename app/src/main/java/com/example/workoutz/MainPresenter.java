package com.example.workoutz;

import android.util.Log;
import android.widget.Toast;

public class MainPresenter {

    private final MainActivity activity;

    public MainPresenter(MainActivity activity) {
        // Initialize the activity variable
        this.activity = activity;

        // Load the saved profiles into memory.  Do this only once
        if (MainModel.getProfileList().isEmpty()) {
            MainModel.loadProfilesFromDevice(activity);
            MainModel.validateProfiles();
        }

        activity.setupProfileList(MainModel.getProfileList());
    }

    public void handleProfileSelected(Profile p) {
        activity.goToProfileDashboard(p);
    }

    public void handleProfileDelete(Profile p) {
        MainModel.deleteProfile(activity, p.id, true);
        Toast.makeText(activity, "Profile deleted.", Toast.LENGTH_SHORT).show();
    }
}
