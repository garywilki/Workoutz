package com.example.workoutz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.util.List;

public class NewProfile extends AppCompatActivity {

    Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_profile);
    }

    public void addProfileButton(View view) {
        // TO DO:
        // Save user-input options as a new Profile

        // Append the new Profile into the profileList
        MainActivity.addProfile(profile);

        // Save profileList data to phone
        MainActivity.saveProfilesToDevice();

        // Go to MainActivity
    }

    public void backButton(View view) {
        // TO DO:
        // Go to MainActivity
        // Do not save or modify the profileList
    }
}