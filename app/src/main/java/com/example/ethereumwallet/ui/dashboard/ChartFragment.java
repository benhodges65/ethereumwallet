package com.example.ethereumwallet.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.ethereumwallet.databinding.FragmentChartBinding;
import com.example.ethereumwallet.R;

public class ChartFragment extends Fragment implements ChartContract.View{

    private FragmentChartBinding binding;
    private ChartContract.Presenter mPresenter;
    private RequestQueue queue;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mPresenter = new ChartViewPresenter();
        mPresenter.setView(this);
        mPresenter.start();
        binding = FragmentChartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        queue = Volley.newRequestQueue(this.getActivity());
        mPresenter.setQueue(queue);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void setPresenter(ChartContract.Presenter presenter) {
        mPresenter = presenter;
    }
}