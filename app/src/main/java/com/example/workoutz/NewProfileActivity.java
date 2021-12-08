package com.example.workoutz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class NewProfileActivity extends AppCompatActivity {

    private NewProfilePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_profile);

        // Start the Presenter class
        presenter = new NewProfilePresenter(this);
    }

    public void addProfileButton(View view) {

        // Obtain user text input
        String name = ((EditText) findViewById(R.id.editTextAddName)).getText().toString();
        String repsString = ((EditText) findViewById(R.id.editReps)).getText().toString();

        int workMinutes = Integer.parseInt(((EditText) findViewById(R.id.editWorkMinutes)).getText().toString());
        int workSeconds = Integer.parseInt(((EditText) findViewById(R.id.editWorkSeconds)).getText().toString()) + (workMinutes * 60);

        int restMinutes = Integer.parseInt(((EditText) findViewById(R.id.editRestMinutes)).getText().toString());
        int restSeconds = Integer.parseInt(((EditText) findViewById(R.id.editRestSeconds)).getText().toString()) + (restMinutes * 60);

        String workIntervalString = Integer.toString(workSeconds);
        String restIntervalString = Integer.toString(restSeconds);

        presenter.handleAddProfileButton(name, repsString, workIntervalString, restIntervalString);
    }

    public void backButton(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToMainActivity() {
        // Go to MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}