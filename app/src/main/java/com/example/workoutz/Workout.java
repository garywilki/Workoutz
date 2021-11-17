package com.example.workoutz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Workout extends AppCompatActivity {

    Profile profile;
    // Timer timer;

    TextView time;
    TextView repsRemaining;
    TextView currentTimer;
    Button pauseButton;
    String timeString;
    WorkoutPresenter presenter;
    Boolean running;
    Boolean finished;
    int profileID;
    Profile p;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        // Obtain the profile ID
        Intent intent = getIntent();
        profileID = intent.getIntExtra(MainModel.EXTRA_INT_PROFILEID, Profile.NULL_ID);
        p = MainModel.getProfile(profileID);

        running = false;
        finished = false;

        time = findViewById(R.id.timer_text);
        repsRemaining = findViewById(R.id.reps_remaining);
        currentTimer = findViewById(R.id.current_timer);

        time.setText("0");

        repsRemaining.setText("5");

        currentTimer.setText("Rest");

        pauseButton = findViewById(R.id.pause_button);

        // Set up the presenter so it has a reference to this activity, as well as the current time
        presenter = new WorkoutPresenter(this, p);
    }

    public void togglePauseButton(View view) {
        // TO DO:
        // Pauses/Unpauses the workout timer

        // Pass the work off to the presenter class
        if (finished == false) {
            if (running == false) {
                timeString = time.getText().toString();
                presenter.startTimer(view);
                running = true;
                pauseButton.setText("Pause");
            } else {
                presenter.pauseTimer(view);
                running = false;
                pauseButton.setText("Unpause");
            }
        } else {
            pauseButton.setText("Workout done");
        }

    }

    public void stopButton(View view) {
        // Ends workout
        // Saving profile data
        // Returns to ProfileDashboard activity
    }

    public void updateTime(String newTime, Boolean finished, int reps, String workState) {
        // Updates the visible time on the screen
        if (newTime != null) {
            time.setText(newTime);
        }
        this.finished = finished;

        currentTimer.setText(workState);

        repsRemaining.setText(String.valueOf(reps));

        if (finished == true) {
            ViewGroup layout = (ViewGroup) pauseButton.getParent();
            if(null!=layout)
                layout.removeView(pauseButton);
                currentTimer.setText("");
        }
    }

    public void backButton(View view) {
        if (p != null) {
            Log.i("MainActivity", "MainActivity.goToProfileDashboard() -> " + p.id + " " + p.name + " " + p.reps + " " + p.workIntervalSeconds + " " + p.restIntervalSeconds + " & " + p.nextID);
            Intent intent = new Intent(this, ProfileDashboard.class);
            intent.putExtra(MainModel.EXTRA_INT_PROFILEID, p.id);
            startActivity(intent);
        }
        else {
            Log.e("MainActivity", "MainActivity.goToProfileDashboard() -> NULL Profile reference");
        }
    }
}