package com.example.workoutz;

import android.content.Intent;
import android.widget.Toast;

public class NewProfilePresenter {

    private final NewProfileActivity activity;

    public NewProfilePresenter(NewProfileActivity activity) {
        // Initialize the activity variable
        this.activity = activity;
    }

    public void handleAddProfileButton(String name, String repsString, String workIntervalString, String restIntervalString) {

        if (name.isEmpty() || repsString.isEmpty() || workIntervalString.isEmpty() || restIntervalString.isEmpty() || Integer.parseInt(repsString) == 0
                || Integer.parseInt(workIntervalString) == 0 || Integer.parseInt(restIntervalString) == 0) {
            // Require the user to enter values in each box
            Toast.makeText(activity, "Please fill in each field", Toast.LENGTH_LONG).show();
        }
        else {
            // Parse the data
            int reps = Integer.parseInt(repsString);
            int workInterval = Integer.parseInt(workIntervalString);
            int restInterval = Integer.parseInt(restIntervalString);

            // Create new Profile based on user-input
            Profile profile = new Profile(name, reps, workInterval, restInterval, 0, 0);

            // Append the new Profile into the profileList and save changes to device
            MainModel.addProfile(activity, profile, true);

            activity.goToMainActivity();
        }
    }
}
