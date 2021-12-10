package com.example.workoutz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class WorkoutActivity extends AppCompatActivity {

    // Variables to access on-screen items and also the WorkoutPresenter and other timer-related items
    TextView time;
    TextView repsRemaining;
    TextView currentTimer;
    ImageButton pauseButton;
    String timeString;
    WorkoutPresenter presenter;
    Boolean running;
    Boolean finished;
    int profileID;
    Profile p;

    // Variables for sound effects and muting them
    MediaPlayer shortBeep;
    MediaPlayer longBeep;
    Boolean muted = false;
    ImageButton muteImageButton;


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

        pauseButton = findViewById(R.id.pause_button);

        muteImageButton = findViewById(R.id.mute_button);

        shortBeep = MediaPlayer.create(this, R.raw.beep_short);
        longBeep = MediaPlayer.create(this, R.raw.beep_long);

        // Set up the presenter so it has a reference to this activity, as well as the current time
        presenter = new WorkoutPresenter(this, p);
    }

    public void togglePauseButton(View view) {
        // Pauses/Unpauses the workout timer

        // Pass the work off to the presenter class
        if (!finished) {
            if (!running) {
                timeString = time.getText().toString();
                presenter.startTimer(view);
                running = true;
                pauseButton.setImageResource(R.drawable.ic_pause);
            } else {
                presenter.pauseTimer(view, false);
                running = false;
                pauseButton.setImageResource(R.drawable.ic_play);
            }
        } else {
            pauseButton.setImageResource(R.drawable.ic_play);
        }

    }

    public void updateTime(String newTime, Boolean finished, int reps, String workState, String beep) {
        // Updates the visible time on the screen
        if (newTime != null) {
            time.setText(newTime);

        }
        this.finished = finished;

        currentTimer.setText(workState);

        repsRemaining.setText(String.valueOf(reps));

        // If the timer isn't muted, play the appropriate sound
        if (!muted) {
            if (beep.equals("short")) {
                shortBeep.start();
            } else if (beep.equals("long")) {
                longBeep.start();
                if (finished) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            longBeep.start();
                        }
                    }, 800);   //1 second delay for second and final timer beep
                }
            }
        }

        if (finished) {
            ViewGroup layout = (ViewGroup) pauseButton.getParent();
            if(null!=layout)
                layout.removeView(pauseButton);
                layout.removeView(currentTimer);
        }
    }

    public void backButton(View view) {
        // RETURN to profile dashboard activity
        if (p != null) {
            Log.i("MainActivity", "MainActivity.goToProfileDashboard() -> " + p.id + " " + p.name + " " + p.reps + " " + p.workIntervalSeconds + " " + p.restIntervalSeconds + " & " + p.nextID);

            // Make sure to stop any currently running timer when back is pressed
            presenter.pauseTimer(view, true);

            Intent intent = new Intent(this, ProfileDashboardActivity.class);
            intent.putExtra(MainModel.EXTRA_INT_PROFILEID, p.id);
            startActivity(intent);
        }
        else {
            Log.e("MainActivity", "MainActivity.goToProfileDashboard() -> NULL Profile reference");
        }
    }

    public void muteButton(View view) {

        // This function controls the state of the muted boolean - and also changes the btn image appropriately

        if (!muted) {
            muted = true;
            muteImageButton.setImageResource(R.drawable.ic_unmute);
        } else {
            muted = false;
            muteImageButton.setImageResource(R.drawable.ic_mute);
        }
    }
}