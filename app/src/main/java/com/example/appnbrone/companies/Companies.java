package com.example.appnbrone.companies;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.appnbrone.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Companies extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    String searchValue;
    String urlStart;
    String urlEnd;
    String url;
    JSONObject response;
    JSONArray results;
    TextView textView = findViewById(R.id.textView2);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companies);
        Log.d(TAG, "!!!!!!!!!!!!!!!!_________testataan_____!!!!!!!!!!!!!!!!!!!!!!!!!!!" );
    }

    @Override
    protected void onStart() {
        super.onStart();

        urlStart = "http://avoindata.prh.fi/bis/v1?totalResults=false&maxResults=75&resultsFrom=0&name=";
        urlEnd ="&companyRegistrationFrom=2000-02-28";

        Bundle extras = getIntent().getExtras();
        if(extras == null){
            return;
        }
        String Value1 = extras.getString("Value1");
        if(Value1 != null){
            searchValue=Value1;
            Log.d(TAG, "Testi: " + searchValue);
            url = urlStart + searchValue + urlEnd;
            Log.d(TAG, "Haussa käytettävä url: " + url);
        }
        handleRequest();
    }

    void handleRequest(){

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Log.d(TAG, "_________testi_____" );

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {

                accessingJson(response);
                Log.d(TAG, "_________testi_____2" );

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error",error.toString());
            }

        });
        //requestQueue.add(request);

    }

    public void accessingJson(JSONObject json) {
        Log.d(TAG, "_________testi_____3" );
        try {
            Object invalid = json.get("invalid"); // throws JSONException - "invalid" entry doesn't exists
        } catch (JSONException e) {
            e.printStackTrace();
        }

        int age = json.optInt("age", 42); // using default value instead of throwing exception
        JSONArray pets = null;
        try {
            results = json.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < results.length(); i++) {
            JSONObject companies = null;
            try {
                companies = results.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String name = null;
            try {
                name = companies.getString("name");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            textView.setText(name);
        }
    }

/*
    public void accessingJson(JSONObject json) {
        Object invalid = json.get("invalid"); // throws JSONException - "invalid" entry doesn't exists
        String name = json.getString("name"); // name = "John Brown"
        int number = json.getInt("name"); // throws JSONException - "name" entry isn't int
        int age = json.optInt("age", 42); // using default value instead of throwing exception
        JSONArray results = json.getJSONArray("results");
        for (int i = 0; i < results.length(); i++) {
            String pet = results.getString(i);
        }
    }

// TÄMÄ TÄÄLLÄ TALLESSA SILTÄ VARALTA ETTÄ SÖSSIN KAIKEN
    public void accessingJson(JSONObject json) {
        try {
            Object invalid = json.get("invalid"); // throws JSONException - "invalid" entry doesn't exists
        } catch (JSONException e) {
            e.printStackTrace();
        }

        int age = json.optInt("age", 42); // using default value instead of throwing exception
        JSONArray pets = null;
        try {
            results = json.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < results.length(); i++) {
            String name = results.getString(i);
        }

    }
    */

}