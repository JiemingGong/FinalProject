package com.example.walking.finaltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView mTextViewResult;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewResult = findViewById(R.id.text_view_result);
        Button buttonParse = findViewById(R.id.button_parse);

        mQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });
    }

    private void jsonParse() {

        //String url = "https://api.iextrading.com/1.0/stock/AAPL/batch?types=quote";
        String url = "https://api.iextrading.com/1.0/stock/market/batch?symbols=aapl,fb,tsla,googl,amzn&types=quote";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //JSONObject object = response.getJSONObject("quote");

                           // String high = object.getString("high");

                            JSONObject apple = response.getJSONObject("AAPL");
                            String appleopen= apple.getJSONObject("quote").getString("open");
                            String appleclose = apple.getJSONObject("quote").getString("close");
                            String percentchange = apple.getJSONObject("quote").getString("changePercent");

                            JSONObject facebook = response.getJSONObject("FB");
                            String fbopen= facebook.getJSONObject("quote").getString("open");
                            String fbclose = facebook.getJSONObject("quote").getString("close");
                            String fbpercentchange = facebook.getJSONObject("quote").getString("changePercent");

                            JSONObject google = response.getJSONObject("GOOGL");
                            String googleopen= facebook.getJSONObject("quote").getString("open");
                            String googleclose = facebook.getJSONObject("quote").getString("close");
                            String googlepercentchange = facebook.getJSONObject("quote").getString("changePercent");

                            JSONObject amazon = response.getJSONObject("AMZN");
                            String amazonopen= facebook.getJSONObject("quote").getString("open");
                            String amazonclose = facebook.getJSONObject("quote").getString("close");
                            String amazonpercentchange = facebook.getJSONObject("quote").getString("changePercent");





                            mTextViewResult.append("Apple open"+" :"+appleopen +"\n"+"Apple close"+appleclose+"\n"+"Apple%:"+percentchange +"\n"+ "Facebook open"+fbopen+"\n"+"Facebook close"+fbclose+"\n"+"Facebook%"+fbpercentchange+"\n"+"Google open"+googleopen+"\n"+"Google close"+googleclose+"\n"+"Google%"+googlepercentchange+"\n"+"Amazon open"+amazonopen +"\n"+"Amazon close"+amazonclose+"\n"+"Amazon%"+amazonpercentchange);
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

        mQueue.add(request);
    }
}