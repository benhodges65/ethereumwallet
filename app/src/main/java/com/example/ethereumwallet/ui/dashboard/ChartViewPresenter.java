package com.example.ethereumwallet.ui.dashboard;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ChartViewPresenter implements ChartContract.Presenter{
    private ChartContract.View mView;
    private RequestQueue queue;
    private String currentPrice;
    private ArrayList<String> weekPrice;
    private ArrayList<String> weekDates;
    private ArrayList<Float> weekTimes;
    private ArrayList<String> monthPrice;
    private ArrayList<String> monthDates;
    private ArrayList<String> yearDates;
    private ArrayList<String> yearPrice;
    private ArrayList<Float> monthTimes;
    private ArrayList<Float> yearTimes;


    @Override
    public void setView(ChartContract.View view) {
        mView = view;
    }

    @Override
    public void start() {
        mView.setPresenter(this);
    }

    @Override
    public void sendCurrentPressed() {
        PopulateDates();
        weekPrice = new ArrayList<String>();
        monthPrice = new ArrayList<String>();
        yearPrice = new ArrayList<String>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String url = "https://api.coinbase.com/v2/prices/ETH-USD/spot";
        List<String> jsonResponses = new ArrayList<>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject = response.getJSONObject("data");
                    String amount = jsonObject.getString("amount");
                    mView.populatePrice(amount);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(jsonObjectRequest);
        for(int i = 0; i < weekDates.size(); i++){
            url = "https://api.coinbase.com/v2/prices/ETH-USD/spot?date=" + weekDates.get(i);
            jsonResponses = new ArrayList<>();
            int finalI = i;
            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONObject jsonObject = response.getJSONObject("data");
                        String amount = jsonObject.getString("amount");
                        Date date = df.parse(weekDates.get(finalI));
                        long epoch = date.getTime();
                        weekTimes.add((float)epoch);
                        weekPrice.add(amount);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    catch (ParseException e){
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            queue.add(jsonObjectRequest);
        }
        for(int i = 0; i < monthDates.size(); i++){
            url = "https://api.coinbase.com/v2/prices/ETH-USD/spot?date=" + monthDates.get(i);
            jsonResponses = new ArrayList<>();
            int finalI = i;
            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONObject jsonObject = response.getJSONObject("data");
                        String amount = jsonObject.getString("amount");
                        Date date = df.parse(monthDates.get(finalI));
                        long epoch = date.getTime();
                        monthTimes.add((float)epoch);
                        monthPrice.add(amount);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    catch (ParseException e){
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            queue.add(jsonObjectRequest);
        }
        for(int i = 0; i < yearDates.size(); i++){
            url = "https://api.coinbase.com/v2/prices/ETH-USD/spot?date=" + yearDates.get(i);
            jsonResponses = new ArrayList<>();
            int finalI = i;
            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONObject jsonObject = response.getJSONObject("data");
                        String amount = jsonObject.getString("amount");
                        Date date = df.parse(yearDates.get(finalI));
                        long epoch = date.getTime();
                        yearTimes.add((float)epoch);
                        yearPrice.add(amount);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    catch (ParseException e){
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            queue.add(jsonObjectRequest);
        }
    }

    public void PopulateDates()
    {
        yearTimes = new ArrayList<Float>();
        monthTimes = new ArrayList<Float>();
        weekTimes = new ArrayList<Float>();
        weekDates = new ArrayList<String>();
        monthDates = new ArrayList<String>();
        yearDates = new ArrayList<String>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentTime = Calendar.getInstance().getTime();
        Calendar calendar = Calendar.getInstance();
        for(int i = -6; i <= 0; i++){
            calendar.setTime(currentTime);
            calendar.add(calendar.DAY_OF_YEAR, i);
            Date tempDate = calendar.getTime();
            String date = dateFormat.format(tempDate);
            weekDates.add(date);
        }
        for(int i = -30; i <= 0; i++){
            calendar.setTime(currentTime);
            calendar.add(calendar.DAY_OF_YEAR, i);
            Date tempDate = calendar.getTime();
            String date = dateFormat.format(tempDate);
            monthDates.add(date);
        }
        for(int i = -11; i <= 0; i++){
            calendar.setTime(currentTime);
            calendar.add(calendar.MONTH, i);
            Date tempDate = calendar.getTime();
            String date = dateFormat.format(tempDate);
            yearDates.add(date);
        }
    }

    @Override
    public void setQueue(RequestQueue queue) {
        this.queue = queue;
    }

    @Override
    public void sendWeeklyPressed() {
        arrayListSort();
        mView.buildChart(weekTimes,weekPrice);
    }

    @Override
    public void sendMonthlyPressed() {
        arrayListSort();
        mView.buildChart(monthTimes,monthPrice);
    }

    @Override
    public void sendYearlyPressed() {
        arrayListSort();
        mView.buildChart(yearTimes,yearPrice);
    }

    public void arrayListSort() {
        for (int i=0; i<weekTimes.size()-1; i++) {
            for (int j=i+1; j<weekTimes.size(); j++) {
                if (weekTimes.get(i) > weekTimes.get(j)) {
                    //... Exchange elements in first array
                    Float temp = weekTimes.get(i);
                    weekTimes.set(i, weekTimes.get(j));
                    weekTimes.set(j, temp);

                    //... Exchange elements in second array
                    String temp2 = weekPrice.get(i);
                    weekPrice.set(i, weekPrice.get(j));
                    weekPrice.set(j, temp2);
                }
            }
        }
        for (int i=0; i<monthTimes.size()-1; i++) {
            for (int j=i+1; j<monthTimes.size(); j++) {
                if (monthTimes.get(i) > monthTimes.get(j)) {
                    //... Exchange elements in first array
                    Float temp = monthTimes.get(i);
                    monthTimes.set(i, monthTimes.get(j));
                    monthTimes.set(j, temp);

                    //... Exchange elements in second array
                    String temp2 = monthPrice.get(i);
                    monthPrice.set(i, monthPrice.get(j));
                    monthPrice.set(j, temp2);
                }
            }
        }
        for (int i=0; i<yearTimes.size()-1; i++) {
            for (int j=i+1; j<yearTimes.size(); j++) {
                if (yearTimes.get(i) > yearTimes.get(j)) {
                    //... Exchange elements in first array
                    Float temp = yearTimes.get(i);
                    yearTimes.set(i, yearTimes.get(j));
                    yearTimes.set(j, temp);

                    //... Exchange elements in second array
                    String temp2 = yearPrice.get(i);
                    yearPrice.set(i, yearPrice.get(j));
                    yearPrice.set(j, temp2);
                }
            }
        }
    }

}
