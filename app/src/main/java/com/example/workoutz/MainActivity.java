package com.example.workoutz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainPresenter presenter;

    Boolean longClick = false;

    // Profile adapter that will handle data in the ListView
    ArrayAdapter<Profile> adapter;
    //

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start the Presenter class
        presenter = new MainPresenter(this);
    }

    /**
     * goToProfileDashboard()
     * Selects a profile and enters the ProfileDashboard activity
     *
     * @param p - The profile that has been selected
     */
    public void goToProfileDashboard(Profile p) {
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

    /**
     * addNewProfileButton()
     * Enters the NewProfile activity
     *
     * @param view - The view object that called the method
     */
    public void addNewProfileButton(View view) {
        Intent intent = new Intent(this, NewProfile.class);
        startActivity(intent);
    }

    /**
     * addNewProfileButton()
     * Enters the NewProfile activity
     *
     * @param profileList - The view object that called the method
     */
    public void setupProfileList(List<Profile> profileList) {
        // Attaches an adapter to the ListView that will create TextViews for each item in our profile list
        // The string used for the TextView is obtained using Profile.toString()
        adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, profileList);
        ListView lv = (ListView)findViewById(R.id.profileListView);
        lv.setAdapter(adapter);

        // When a profile entry is pressed, send a reference to the profile to the presenter
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!longClick) {
                    Profile p = adapter.getItem(Math.toIntExact(id));
                    presenter.handleProfileSelected(p);
                }
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                longClick = true;
                Profile p = adapter.getItem(Math.toIntExact(id));

                //Begin dialogue

                String dialog_message = "Are you sure you want to delete this profile?";
                String dialog_title = "Delete Profile";

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setMessage(dialog_message)
                        .setTitle(dialog_title);

                // Go from initiating builder to setting buttons

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        presenter.handleProfileDelete(p);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface arg0) {
                        longClick = false;
                    }
                });

                AlertDialog dialog = builder.create();
                builder.show();

                // End dialogue

                return false;
            }
        });
    }

}