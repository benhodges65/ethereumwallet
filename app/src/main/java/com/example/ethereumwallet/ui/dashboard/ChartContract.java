package com.example.ethereumwallet.ui.dashboard;

public interface ChartContract {
    interface View{
        public void setPresenter(Presenter presenter);
    }
    interface Presenter{
        public void setView(View view);
        public void start();
    }
}
