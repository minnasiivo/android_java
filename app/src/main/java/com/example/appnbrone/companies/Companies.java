package com.example.appnbrone.companies;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;
import java.util.List;

public class Companies extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    String searchValue;
    String urlStart;
    String urlEnd;
    String myUrl;
    //JSONObject response;
    JSONArray results;
    TextView textView;
    List<String> allNames;
    List<String> companyList;
    List<String> regDateList;
    List<String> companyFormList;

    private RecyclerView mRecyclerView;
    private RecycleAdapter mAdapter;
    private List<Detail> mList = new ArrayList<>();


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companies);

        mRecyclerView = (RecyclerView) findViewById(R.id.cardList);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 5));
        mAdapter = new RecycleAdapter(getApplicationContext(), mList);
        mRecyclerView.setAdapter(mAdapter);

    }

    /**
     * Called when a grid item is clicked
     *
     * @param input The value of the grid item which is displayed by position
     */
    private void makeToast(String input) {
        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
        }



    @Override
    protected void onStart() {
        super.onStart();
        textView = findViewById(R.id.textView2);
        urlStart = "http://avoindata.prh.fi/bis/v1?totalResults=false&maxResults=50&resultsFrom=0&name=";
        urlEnd ="&companyRegistrationFrom=2000-02-28";

        Bundle extras = getIntent().getExtras();
        if(extras == null){
            return;
        }
        String Value1 = extras.getString("Value1");
        if(Value1 != null){
            searchValue=Value1;
            Log.d(TAG, "Testi: " + searchValue);
            myUrl = urlStart + searchValue + urlEnd;
            Log.d(TAG, "Haussa käytettävä url: " + myUrl);
        }
        handleRequest();


    }



    void handleRequest(){


        Log.d(TAG, "_________testi_____" );

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                myUrl,
                null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "heissulivei");
                        Log.d(TAG, response.toString());
                        //accessingJsonArray(response);
                        //accessingJson(response);

                        try{
                        JSONArray results= null;

                            results = response.getJSONArray("results");
                            Log.d(TAG, results.toString());
                            allNames = new ArrayList<String>();
                            companyList = new ArrayList<String>();
                            companyFormList = new ArrayList<String>();
                            regDateList = new ArrayList<String>();


                            for(int i=0; i<results.length(); i++){
                                JSONObject companies = null;
                                companies = results.getJSONObject(i);
                                String name = null;
                                name = companies.getString("name");
                                String bID= companies.getString("businessId");
                                String form = companies.getString("companyForm");
                                String regDate = companies.getString("registrationDate");
                                allNames.add(name);
                                companyList.add(bID);
                                companyFormList.add(form);
                                regDateList.add(regDate);

                                Log.d("name",allNames.get(i) );
                                Log.d("ID:", companyList.get(i));
                                Log.d("CompanyForm:", companyFormList.get(i));
                                Log.d("RegistrationDate:", regDateList.get(i));
                                textView.setText("NAME: "+ allNames.get(i)+" ID: "+companyList.get(i) + companyFormList.get(i) + regDateList.get(i));

                                mList.add(new Detail("name","bID","form","regDate",i));

                            }



                            // Tässä tehdään listaus rajapinnasta haetusta tiedosta välitettäväksi adapterille..


                            mAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    makeToast("" + mList.get(position).getPosition());
                                }
                            });


                        }catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error",error.toString());
                        Log.d(TAG, " virheen etsintää..." );
                        return;
                    }

                });
        requestQueue.add(request);

    }
/*
    List<String> allNames = new ArrayList<String>();

    JSONArray cast = jsonResponse.getJSONArray("abridged_cast");
for (int i=0; i<cast.length(); i++) {
        JSONObject actor = cast.getJSONObject(i);
        String name = actor.getString("name");
        allNames.add(name);
    }*/

    private void accessingJsonArray(JSONArray response) {

    try{
        JSONObject nameObj = null;
         nameObj = response.getJSONObject(0);
        Log.d("TAG", nameObj.getString("type"));

        results = nameObj.getJSONArray("results");

        for (int i = 0; i < results.length(); i++) {
            JSONObject companies = null;
                companies = results.getJSONObject(i);

            String name = null;
                name = companies.getString("name");
            Log.d("name",name);
            textView.setText(name);
        }

    }catch (JSONException e) {
        e.printStackTrace();
    }}


    public void accessingJson(JSONObject response) {
        Log.d(TAG, "_________testi_____3" );

        JSONArray results= null;
        try{
        results = response.getJSONArray("results");
        for(int i=0; i<results.length(); i++){
            JSONObject companies = null;
            companies = results.getJSONObject(i);
            String name = null;
            name = companies.getString("name");
            Log.d("name",name);
            textView.setText(name);

        }

        }catch (JSONException e) {
                e.printStackTrace();
            }
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

