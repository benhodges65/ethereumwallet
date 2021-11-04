package com.example.ethereumwallet.ui.home;



public class WalletViewPresenter implements  WalletContract.Presenter{
    private WalletContract.View mView;

    @Override
    public void setView(WalletContract.View view) {
        mView = view;
    }

    @Override
    public void start() {
        mView.setPresenter(this);
    }
}
