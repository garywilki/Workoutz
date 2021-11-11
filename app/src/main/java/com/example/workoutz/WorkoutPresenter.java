package com.example.workoutz;

import android.app.Activity;
import android.view.View;

public class WorkoutPresenter {

    private String time;
    private Workout activity;


    public WorkoutPresenter(String time, Workout activity) {

        // Initialize the time and activity variables
        this.time = time;
        this.activity = activity;
    }

    public void handleButton(View view) {

        // Set a new time TO BE REPLACED BY ACTUALLY TIMERS
        time = "0:01";

        // Call workout.updateTime() to make the new time display on the screen
        activity.updateTime(time);
    }
}
