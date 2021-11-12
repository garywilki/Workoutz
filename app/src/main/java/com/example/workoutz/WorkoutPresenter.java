package com.example.workoutz;

import android.app.Activity;
import android.os.CountDownTimer;
import android.view.View;

public class WorkoutPresenter {

    private Workout activity;
    private CountDownTimer timer;
    public int currentMillis;


    public WorkoutPresenter(Workout activity, String timeString) {

        // Initialize the time and activity variables
        this.activity = activity;
        this.currentMillis = 610000;

        calculateTime(currentMillis);


    }

    public void startTimer(View view) {

        this.timer = new CountDownTimer(currentMillis, 10) {

            public void onTick(long millisUntilFinished) {
                currentMillis = (int) millisUntilFinished;
                calculateTime(currentMillis);
            }

            public void onFinish() {
                activity.updateTime("Finished!", true);
            }

        }.start();
    }

    public void pauseTimer(View view) {
        this.timer.cancel();
    }

    public void calculateTime(int millis) {
        String newTime;

        int totalSeconds = (millis / 1000);
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;

        if (seconds < 10) {
            newTime = minutes + ":0" + seconds;
        } else {
            newTime = minutes + ":" + seconds;
        }

        activity.updateTime(newTime, false);
    }
}
