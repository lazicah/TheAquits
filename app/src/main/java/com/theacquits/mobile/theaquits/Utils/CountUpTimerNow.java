package com.theacquits.mobile.theaquits.Utils;


import android.text.format.Time;

/**
 * Created by root on 2/16/19.
 */

public class CountUpTimerNow {
    private long intervalMillis;
    public CountUpTimerNow(int second, int minute, int hour, int monthDay, int month, int year) {
        Time futureTime = new Time();
        // Set date to future time
        futureTime.set(second, minute, hour, monthDay, month, year);
        futureTime.normalize(true);
        long futureMillis = futureTime.toMillis(true);
        Time timeNow = new Time();
        // Set date to current time
        timeNow.setToNow();
        timeNow.normalize(true);
        long nowMillis = timeNow.toMillis(true);
        // Subtract current milliseconds time from future milliseconds time to retrieve interval
        intervalMillis = nowMillis - futureMillis;
    }
    public long getIntervalMillis() {
        return intervalMillis;
    }
}
