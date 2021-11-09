package com.example.workoutz;

import java.util.ArrayList;

public class Profile {

    String name;
    int reps;
    int workIntervalSeconds;
    int restIntervalSeconds;
    int totalDays;
    int totalTime;
    //ArrayList<Integer> recentTimes;

    public Profile() {

    }

    public Profile(String name, int reps, int workIntervalSeconds, int restIntervalSeconds, int totalDays, int totalTime) {
        this.name = name;
        this.reps = reps;
        this.workIntervalSeconds = workIntervalSeconds;
        this.restIntervalSeconds = restIntervalSeconds;
        this.totalDays = totalDays;
        this.totalTime = totalTime;
    }
}
