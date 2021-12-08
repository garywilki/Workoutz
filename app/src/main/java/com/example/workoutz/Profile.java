package com.example.workoutz;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;


public class Profile {

    public static int nextID = 0;
    public static final int NULL_ID = -1;
    public static final int NUMBER_OF_HISTORY_DAYS = 7;
    public int id;

    String name;
    public int reps;
    public int workIntervalSeconds;
    public int restIntervalSeconds;

    ArrayList<Integer> recentTimes;
    Date lastUpdatedDate;


    public Profile(String name, int reps, int workIntervalSeconds, int restIntervalSeconds, int totalDays, int totalTime) {
        this.id = nextID++; // Will give each Profile object a unique ID number

        this.name = name;
        this.reps = reps;
        this.workIntervalSeconds = workIntervalSeconds;
        this.restIntervalSeconds = restIntervalSeconds;

        recentTimes = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_HISTORY_DAYS; i++) {
            recentTimes.add(0); // Initialize the history for past 7 days
        }
        lastUpdatedDate = getDateWithoutTime();
    }

    public static Date getDateWithoutTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    @Override
    public String toString() {
        return name;
    }

    public void addTime(int seconds) {
        this.updateHistoryDates();
        int latestSeconds = recentTimes.get(recentTimes.size()-1);
        recentTimes.set(recentTimes.size()-1, latestSeconds + seconds); // Set the final value of the recent array
    }

    public void updateHistoryDates() {
        final int MILLISECONDS_PER_DAY = 24*60*60*1000;
        Date currentDate = getDateWithoutTime();
        if (currentDate.compareTo(this.lastUpdatedDate) == 0) {
            // If the dates match, no history shifting is needed
        }
        else {
            // Convert the current and lastUpdated dates to milliseconds
            long currentMillis = currentDate.getTime();
            long lastUpdatedMillis = this.lastUpdatedDate.getTime();

            // Determine how many days have passed
            long differenceMillis = currentMillis - lastUpdatedMillis;
            int differenceDays = (int)(differenceMillis / MILLISECONDS_PER_DAY);

            // Shift the data array based on the number of days that have changed
            if (differenceDays > 0) {
                for (int i = 0; i < recentTimes.size(); i++) {
                    if (i + differenceDays < recentTimes.size()) {
                        recentTimes.set(i, recentTimes.get(i + differenceDays));
                    } else {
                        recentTimes.set(i, 0);
                    }
                }
            }
            else {
                differenceDays = Math.abs(differenceDays);
                for (int i = recentTimes.size() - 1; i >= 0; i--) {
                    if (i - differenceDays >= 0) {
                        recentTimes.set(i, recentTimes.get(i - differenceDays));
                    } else {
                        recentTimes.set(i, 0);
                    }
                }
            }

            // Finally, set the lastUpdated date to the current date
            this.lastUpdatedDate = currentDate;
        }
    }
}
