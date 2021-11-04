package com.example.ethereumwallet.ui.dashboard;

public class ChartViewPresenter implements ChartContract.Presenter{
    private ChartContract.View mView;

    @Override
    public void setView(ChartContract.View view) {
        mView = view;
    }

    @Override
    public void start() {
        mView.setPresenter(this);
    }
}
