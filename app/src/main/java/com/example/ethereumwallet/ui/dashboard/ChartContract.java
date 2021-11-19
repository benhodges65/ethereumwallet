package com.example.ethereumwallet.ui.dashboard;

import com.android.volley.RequestQueue;

public interface ChartContract {
    interface View{
        public void setPresenter(Presenter presenter);
    }
    interface Presenter{
        public void setView(View view);
        public void start();
        public void sendCurrentPressed();
        public void setQueue(RequestQueue queue);
    }
}
