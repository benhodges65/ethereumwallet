package com.example.ethereumwallet.ui.dashboard;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.ethereumwallet.MainActivity;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Hex;

public class ChartViewPresenter implements ChartContract.Presenter{
    private ChartContract.View mView;
    private RequestQueue queue;

    @Override
    public void setView(ChartContract.View view) {
        mView = view;
    }

    @Override
    public void start() {
        mView.setPresenter(this);
    }

    @Override
    public void sendCurrentPressed() {
        String url = "https://api.coinbase.com/v2/prices/BTC-USD/spot?date=2020-10-21";
        List<String> jsonResponses = new ArrayList<>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject = response.getJSONObject("data");
                    String email = jsonObject.getString("amount");
                    mView.populatePrice(email);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(jsonObjectRequest);
    }

    @Override
    public void setQueue(RequestQueue queue) {
        this.queue = queue;
    }

}
