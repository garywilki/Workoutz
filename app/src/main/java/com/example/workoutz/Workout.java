package com.example.workoutz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Workout extends AppCompatActivity {

    Profile profile;
    // Timer timer;

    TextView time;
    Button pauseButton;
    String timeString;
    WorkoutPresenter presenter;
    Boolean running;
    Boolean finished;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        running = false;
        finished = false;

        timeString = "10";

        time = findViewById(R.id.timer_text);
        time.setText(timeString);

        pauseButton = findViewById(R.id.pause_button);

        // Set up the presenter so it has a reference to this activity, as well as the current time
        presenter = new WorkoutPresenter(this);
    }

    public void togglePauseButton(View view) {
        // TO DO:
        // Pauses/Unpauses the workout timer

        // Pass the work off to the presenter class
        if (finished == false) {
            if (running == false) {
                timeString = time.getText().toString();
                presenter.startTimer(view, timeString);
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

    public void updateTime(String newTime, Boolean finished) {
        // Updates the visible time on the screen
        time.setText(newTime);
        this.finished = finished;

        if (finished == true) {
            ViewGroup layout = (ViewGroup) pauseButton.getParent();
            if(null!=layout)
                layout.removeView(pauseButton);
        }
    }
}