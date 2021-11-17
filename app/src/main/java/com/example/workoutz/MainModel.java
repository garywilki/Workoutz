package com.example.workoutz;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainModel {

    private static List<Profile> profileList = new ArrayList<>();
    private static Gson gson = new Gson();

    // Used for passing profile IDs to other activities
    public static final String EXTRA_INT_PROFILEID = "com.example.workoutz.PROFILEID";

    /**
     * loadProfilesFromDevice()
     * Loads all profile data from device into memory
     */
    public static void loadProfilesFromDevice(Activity activity) {

        // Populate the profile list by reading the data stored in the phone
        SharedPreferences sharedPref = activity.getSharedPreferences("Data", Context.MODE_PRIVATE);
        String jsonProfileList = sharedPref.getString("ProfileList", "");
        Type type = new TypeToken<List<Profile>>(){}.getType();
        MainModel.profileList = gson.fromJson(jsonProfileList, type);

        if (MainModel.profileList == null) {
            MainModel.profileList = new ArrayList<>();
        }

        // Reset the internal IDs for each profile
        int nextID = 0;
        for (Profile p : profileList) {
            p.id = nextID++;
        }
        Profile.nextID = nextID;

        // For testing purposes only.  Remove in final version.
/*        Profile profile = new Profile("George Washington",3,10,5,2,30);
        MainModel.profileList.add(profile);
        profile = new Profile("Thomas Jefferson",2,3,5,100,100);
        MainModel.profileList.add(profile);*/
    }

    /**
     * saveProfilesFromDevice()
     * Saves all profile data from memory to device
     */
    public static void saveProfilesToDevice(Activity activity) {
        String jsonProfileList = gson.toJson(MainModel.profileList);
        SharedPreferences sharedPref = activity.getSharedPreferences("Data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("ProfileList", jsonProfileList);
        editor.apply();
    }

    /**
     * addProfile()
     * Adds a new profile to the profile list
     *
     * @param p - the new profile to be added to the list
     */
    public static void addProfile(Activity activity, Profile p, boolean saveChangesToDevice) {
        MainModel.profileList.add(p);
        if (saveChangesToDevice) {
            saveProfilesToDevice(activity); // Immediately update the device record
        }
    }

    /**
     * deleteProfile()
     * Adds a new profile to the profile list
     *
     * @param profileID - the unique ID of the profile
     */
    public static void deleteProfile(Activity activity, long profileID, boolean saveChangesToDevice) {
        MainModel.profileList.removeIf( profile -> profile.id == profileID );
        if (saveChangesToDevice) {
            saveProfilesToDevice(activity); // Immediately update the device record
        }
    }

    /**
     * getProfile()
     * Obtains a reference to a profile based on the specified ID
     *
     * @param profileID - the unique ID of the profile
     */
    public static Profile getProfile(int profileID) {
        for (Profile p : MainModel.profileList) {
            if (p.id == profileID) {
                return p;
            }
        }
        return null;
    }

    /**
     * getProfileList()
     * Obtains a reference to the profile list
     */
    public static List<Profile> getProfileList() {
        return MainModel.profileList;
    }


}
