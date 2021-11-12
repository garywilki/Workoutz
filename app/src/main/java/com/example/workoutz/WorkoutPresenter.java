package com.example.workoutz;

import android.app.Activity;
import android.os.CountDownTimer;
import android.view.View;

public class WorkoutPresenter {

    private int timeInt;
    private Workout activity;
    private CountDownTimer timer;


    public WorkoutPresenter(Workout activity) {

        // Initialize the time and activity variables
        this.activity = activity;


    }

    public void startTimer(View view, String timeString) {

        // Set a new time TO BE REPLACED BY ACTUALLY TIMERS
        timeInt = Integer.parseInt(timeString) * 1000;
        this.timer = new CountDownTimer(timeInt, 1000) {

            public void onTick(long millisUntilFinished) {
                activity.updateTime(millisUntilFinished / 1000 + "", false);
            }

            public void onFinish() {
                activity.updateTime("Finished!", true);
            }

        }.start();
    }

    public void pauseTimer(View view) {
        this.timer.cancel();
    }
}
