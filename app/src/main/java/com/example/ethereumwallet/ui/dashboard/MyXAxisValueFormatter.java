package com.example.ethereumwallet.ui.dashboard;

import android.util.Log;

import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyXAxisValueFormatter extends IndexAxisValueFormatter {
    @Override
    public String getFormattedValue(float value) { ;
        // Convert float value to date string
        // Convert from days back to milliseconds to format time  to show to the user
        long emissionsMilliSince1970Time = (long) value;
        // Show time in local version
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(emissionsMilliSince1970Time);
        Log.d("TEST", formatter.format(calendar.getTime()));
        return formatter.format(calendar.getTime());
    }
}
