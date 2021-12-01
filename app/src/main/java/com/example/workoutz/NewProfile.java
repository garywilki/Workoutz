package com.example.workoutz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class NewProfile extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_profile);
    }

    public void addProfileButton(View view) {

        // Obtain user text input
        String name = ((EditText) findViewById(R.id.editTextAddName)).getText().toString();
        String repsString = ((EditText) findViewById(R.id.editReps)).getText().toString();

        int workMinutes = Integer.valueOf(((EditText) findViewById(R.id.editWorkMinutes)).getText().toString());
        int workSeconds = Integer.valueOf(((EditText) findViewById(R.id.editWorkSeconds)).getText().toString()) + (workMinutes * 60);

        int restMinutes = Integer.valueOf(((EditText) findViewById(R.id.editRestMinutes)).getText().toString());
        int restSeconds = Integer.valueOf(((EditText) findViewById(R.id.editRestSeconds)).getText().toString()) + (restMinutes * 60);

        String workIntervalString = Integer.toString(workSeconds);
        String restIntervalString = Integer.toString(restSeconds);

        if (name.isEmpty() || repsString.isEmpty() || workIntervalString.isEmpty() || restIntervalString.isEmpty() || Integer.valueOf(repsString) == 0
                || Integer.valueOf(workIntervalString) == 0 || Integer.valueOf(restIntervalString) == 0) {
            // Require the user to enter values in each box
            Toast.makeText(this, "Please fill in each field", Toast.LENGTH_LONG).show();
        }
        else {
            // Parse the data
            int reps = Integer.parseInt(repsString);
            int workInterval = Integer.parseInt(workIntervalString);
            int restInterval = Integer.parseInt(restIntervalString);

            // Create new Profile based on user-input
            Profile profile = new Profile(name, reps, workInterval, restInterval, 0, 0);

            // Append the new Profile into the profileList and save changes to device
            MainModel.addProfile(this, profile, true);

            // Go to MainActivity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void backButton(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}