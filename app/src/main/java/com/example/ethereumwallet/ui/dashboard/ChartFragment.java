package com.example.ethereumwallet.ui.dashboard;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

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
        RadioButton monthlyView = root.findViewById(R.id.radioMonthly);
        RadioButton yearlyView = root.findViewById(R.id.radioYearly);
        RadioGroup radioGroup = root.findViewById(R.id.radioGroup);
        radioGroup.check(R.id.radioWeekly);
        weeklyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.sendWeeklyPressed();
            }
        });
        monthlyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.sendMonthlyPressed();
            }
        });
        yearlyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.sendYearlyPressed();
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
    public void buildChart(ArrayList<Float> dates, ArrayList<String> prices) {
        ArrayList<Entry> lineEntries = new ArrayList<Entry>();
        for(int i = 0; i < prices.size(); i++) {
            lineEntries.add(new Entry(dates.get(i), Float.valueOf(prices.get(i))));
        }
        LineDataSet lineDataSet = new LineDataSet(lineEntries, "Ethereum Price");
        LineData lineData = new LineData(lineDataSet);
        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new MyXAxisValueFormatter());
        if(prices.size() == 7){
            xAxis.setLabelCount(prices.size(), true);
        }
        else
            xAxis.setLabelCount(12, true);
        chart.setData(lineData);
        chart.invalidate();
    }

}