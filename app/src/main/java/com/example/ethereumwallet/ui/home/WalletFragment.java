package com.example.ethereumwallet.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.ethereumwallet.databinding.FragmentWalletBinding;
import com.example.ethereumwallet.ui.dashboard.ChartViewPresenter;

public class WalletFragment extends Fragment implements WalletContract.View{

    private FragmentWalletBinding binding;
    private WalletContract.Presenter mPresenter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentWalletBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mPresenter = new WalletViewPresenter();
        mPresenter.setView(this);
        mPresenter.start();
        final TextView textView = binding.textHome;
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void setPresenter(WalletContract.Presenter presenter) {
        mPresenter = presenter;
    }
}