package com.example.workoutz;

import android.os.CountDownTimer;
import android.view.View;

public class WorkoutPresenter {

    // Initialize variables for the activity, timer and currentMillis
    private WorkoutActivity activity;
    private CountDownTimer timer;
    public int currentMillis;

    // Initialize profile interval and rep variables
    int pReps;
    int pWork;
    int pRest;
    Boolean working = false; // To know whether or not it's a rest or work interval
    Boolean preWorkout = true; // Tracks whether or not the preworkout countdown timer is active
    Boolean running = false;
    Boolean exitingWorkout = false;
    String beepType = ""; // Used to convey the appropriate type (length) of beep to the WorkoutActivity
    int profID;
    int timeElapsed; // Used for tracking time spent for logging in the graph on the Dashboard


    public WorkoutPresenter(WorkoutActivity activity, Profile p) {

        // Initialize the time and activity variables
        this.activity = activity;
        this.currentMillis = 4000;

        //set profile variables for workout, from profile data
        profID = p.id;
        pReps = p.reps;
        pWork = p.workIntervalSeconds * 1000;
        pRest = p.restIntervalSeconds * 1000;

        calculateTime(currentMillis);


    }

    public void startTimer(View view) {

        // Initiates countdown timer based on profile information and other relevant values

        running = true;

        this.timer = new CountDownTimer(currentMillis, 10) {

            public void onTick(long millisUntilFinished) {
                currentMillis = (int) millisUntilFinished;
                if (working) {
                    timeElapsed = (int) Math.round((float)(pWork - currentMillis) / 1000);
                } else {
                    timeElapsed = (int) Math.round((float)(pRest - currentMillis) / 1000);
                }
                calculateTime(currentMillis);
            }

            public void onFinish() {
                // If reps goes down to one, finish the workout. Otherwise, start a new timer with the correct values
                if (pReps <= 1 && working == true) {
                    MainModel.addProfileTime(activity, profID, timeElapsed);
                    working = false;
                    if (pReps >=1) {
                        pReps -= 1;
                    }
                    activity.updateTime("Finished!", true, pReps, "Rest", "long");
                    running = false;
                } else {
                    if (preWorkout) {
                        // Make sure the pre-workout prep time ends by setting preWorkout to false
                        preWorkout = false;

                        // Move into the first Work section
                        working = true;

                        // Update the timer currentMillis value to have the correct work interval value
                        currentMillis = pWork;
                        activity.updateTime(null, false, pReps, "Rest", "long");

                        // Use recursion to start the next timer in the chain, if the current timer finishes
                        if (!exitingWorkout) {
                            startTimer(view);
                        }
                    } else {
                        if (!working) {

                            // Follow same update procedure as above every time we switch from Work to Rest and vice versa
                            MainModel.addProfileTime(activity, profID, timeElapsed);
                            working = true;
                            if (pReps >=1) {
                                pReps -= 1;
                            }
                            currentMillis = pWork;
                            activity.updateTime(null, false, pReps, "Work", "long");
                            startTimer(view);
                        } else if (working) {
                            MainModel.addProfileTime(activity, profID, timeElapsed);
                            working = false;
                            currentMillis = pRest;
                            activity.updateTime(null, false, pReps, "Rest", "long");
                            startTimer(view);
                        }
                    }
                }
            }

        }.start();
    }

    public void pauseTimer(View view, Boolean exiting) {
        // Cancels current countdown timer

        exitingWorkout = exiting;
        if (this.timer != null) {
            if (exiting) {
                pReps = 0;
                this.timer.onFinish();
                running = false;
            } else if (!exiting) {
                running = false;
            }
            this.timer.cancel();
        }
    }

    public void calculateTime(int millis) {

        // This calculates the time and puts it in a displayable format for the UI to be updated
        String newTime;

        int totalSeconds = (millis / 1000);
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60 + 1;

        if (seconds < 10) {
            newTime = minutes + ":0" + seconds;
        } else {
            newTime = minutes + ":" + seconds;
        }

        // Check if seconds changes to 3, 2, or 1, and if so, set the beep type

        if (millis <= 10050 && millis > 10000 || millis <= 3050 && millis > 3000 || millis <= 2050 && millis > 2000 || millis <= 1050 && millis > 1000) {
            beepType = "short";
        } else {
            beepType = "none";
        }

        // Adjust the current work state based on appropriate boolean values

        String workState;

        if (preWorkout) {
            workState = "Get ready!";
        } else if (working) {
            workState = "Work";
        } else {
            workState = "Rest";
        }

        // Send everything to the Workout Activity to update what's displayed

        activity.updateTime(newTime, false, pReps, workState, beepType);
    }
}
