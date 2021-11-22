package com.example.ethereumwallet.ui.dashboard;

import com.android.volley.RequestQueue;

import java.util.ArrayList;

public interface ChartContract {
    interface View{
        public void setPresenter(Presenter presenter);
        public void populatePrice(String price);
        public void buildChart(ArrayList<Float> dates, ArrayList<String> prices);
    }
    interface Presenter{
        public void setView(View view);
        public void start();
        public void sendCurrentPressed();
        public void setQueue(RequestQueue queue);
        public void sendWeeklyPressed();
        public void sendMonthlyPressed();
        public void sendYearlyPressed();
    }
}
