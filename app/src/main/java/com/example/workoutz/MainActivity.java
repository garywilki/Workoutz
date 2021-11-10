package com.example.workoutz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Private list.  Other classes should use public static methods to access
    private static List<Profile> profileList;

    // Used for passing profile IDs to other activities
    public static final String EXTRA_INT_PROFILEID = "com.example.scripture.PROFILEID";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadProfilesFromDevice();
    }

    public void drDoLittle() {
        //I do nothing!
    }

    /*
        loadProfilesFromDevice
        Loads all profile data from device into memory
     */
    public static void loadProfilesFromDevice() {
        List<Profile> profileList = new ArrayList<>();
        // TO DO:
        // Load profile data from the phone
        Profile profile = new Profile();
        profile.name = "George Washington";
        profileList.add(profile);
        MainActivity.profileList = profileList;
    }

    /*
        saveProfilesFromDevice
        Saves all profile data to device from memory
     */
    public static void saveProfilesToDevice() {
        // TO DO:
        // Save profile data to the phone
    }

    /*
        getProfile
        @param int profileID - the unique ID of the profile
        Obtains a reference to a profile based on the specified ID
     */
    public static Profile getProfile(int profileID) {
        for (Profile p : MainActivity.profileList) {
            if (p.id == profileID) {
                return p;
            }
        }
        return null;
    }

    /*
        addProfile
        @param Profile p - the new profile to be added to the list
        Adds a new profile to the profile list
     */
    public static void addProfile(Profile p) {
        MainActivity.profileList.add(p);
    }

    /*
        selectProfileButton
        @param View view - The view object that called the method
        Selects a profile and enters the ProfileDashboard activity
     */
    public void selectProfileButton(View view) {
        // TO DO:
        // Replace the next line with the ID of the selected profile
        int profileID = MainActivity.profileList.get(0).id;

        Intent intent = new Intent(this, ProfileDashboard.class);
        intent.putExtra(EXTRA_INT_PROFILEID, profileID);
        startActivity(intent);
    }

    /*
        addNewProfileButton
        @param View view - The view object that called the method
        Enters the NewProfile activity
     */
    public void addNewProfileButton(View view) {
        Intent intent = new Intent(this, NewProfile.class);
        startActivity(intent);
    }

    public void doNothing() {
        int i = 0;
    }
}