package com.example.ethereumwallet.ui.home;


public interface WalletContract {
    interface View{
        public void setPresenter(Presenter presenter);
    }
    interface Presenter{
        public void setView(View view);
        public void start();
    }
}
