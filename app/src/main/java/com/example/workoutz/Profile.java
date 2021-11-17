package com.example.workoutz;

import java.util.ArrayList;

public class Profile {

    public static int nextID = 0;
    public static final int NULL_ID = -1;
    public int id;

    String name;
    public int reps;
    public int workIntervalSeconds;
    public int restIntervalSeconds;
    public int totalDays;
    public int totalTime;
    //ArrayList<Integer> recentTimes;

    public Profile(String name, int reps, int workIntervalSeconds, int restIntervalSeconds, int totalDays, int totalTime) {
        this.id = nextID++; // Will give each Profile object a unique ID number

        this.name = name;
        this.reps = reps;
        this.workIntervalSeconds = workIntervalSeconds;
        this.restIntervalSeconds = restIntervalSeconds;
        this.totalDays = totalDays;
        this.totalTime = totalTime;
    }

    @Override
    public String toString() {
        return name;
    }
}
