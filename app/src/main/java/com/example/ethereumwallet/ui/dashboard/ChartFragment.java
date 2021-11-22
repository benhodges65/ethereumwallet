package com.example.ethereumwallet.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.github.mikephil.charting.charts.LineChart;

import java.util.ArrayList;

public class ChartFragment extends Fragment implements ChartContract.View{

    private FragmentChartBinding binding;
    private ChartContract.Presenter mPresenter;
    private RequestQueue queue;
    private TextView mPrice;
    private LineChart chart;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mPresenter = new ChartViewPresenter();
        mPresenter.setView(this);
        mPresenter.start();
        binding = FragmentChartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        queue = Volley.newRequestQueue(this.getActivity());
        mPresenter.setQueue(queue);
        mPrice = root.findViewById(R.id.currentPrice);
        mPresenter.sendCurrentPressed();
        chart = root.findViewById(R.id.chartBox);
        RadioButton weeklyView = root.findViewById(R.id.radioWeekly);
        RadioButton monthlyView = root.findViewById(R.id.radioWeekly);
        RadioButton yearlyView = root.findViewById(R.id.radioWeekly);
        RadioGroup radioGroup = root.findViewById(R.id.radioGroup);
        radioGroup.check(R.id.radioWeekly);
        weeklyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        monthlyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        yearlyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
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

    @Override
    public void populatePrice(String price) {
        mPrice.setText(price);
    }

    @Override
    public void buildChart(ArrayList<String> dates, ArrayList<String> prices) {
    }

}