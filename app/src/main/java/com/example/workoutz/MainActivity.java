package com.example.workoutz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static List<Profile> profileList;
    public static final String EXTRA_INT_PROFILEID = "com.example.scripture.PROFILEID";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity.profileList = loadProfilesFromDevice();
    }

    public List<Profile> loadProfilesFromDevice() {
        List<Profile> profileList = new ArrayList<>();
        // TO DO:
        // Load profile data from the phone
        // Return data as a List of profiles
        Profile profile = new Profile();
        profile.name = "Bobby Fischer";
        profileList.add(profile);
        return profileList;
    }

    public void selectProfileButton(View view) {
        // TO DO:
        // Replace the next line with the ID of the selected profile
        int profileID = 0;

        Intent intent = new Intent(this, ProfileDashboard.class);
        intent.putExtra(EXTRA_INT_PROFILEID, profileID);
        startActivity(intent);
    }

    public void addNewProfileButton(View view) {
        Intent intent = new Intent(this, NewProfile.class);
        startActivity(intent);
    }
}