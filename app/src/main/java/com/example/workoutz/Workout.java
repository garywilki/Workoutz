package com.example.workoutz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Workout extends AppCompatActivity {

    Profile profile;
    // Timer timer;

    TextView time;
    String timeString;
    WorkoutPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        time = findViewById(R.id.timer_text);
        timeString = time.getText().toString();

        // Set up the presenter so it has a reference to this activity, as well as the current time
        presenter = new WorkoutPresenter(timeString, this);
    }

    public void togglePauseButton(View view) {
        // TO DO:
        // Pauses/Unpauses the workout timer

        // Pass the work off to the presenter class
        presenter.handleButton(view);
    }

    public void stopButton(View view) {
        // Ends workout
        // Saving profile data
        // Returns to ProfileDashboard activity
    }

    public void updateTime(String newTime) {
        // Updates the visible time on the screen
        time.setText(newTime);
    }
}