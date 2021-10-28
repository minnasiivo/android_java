package com.example.appnbrone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";
    private TextView helloView;
    //AirplanemodeReceiver airplanemodeReceiver = new AirplanemodeReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "main activity käynnistyy");
        //IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        //registerReceiver(airplanemodeReceiver, filter);





        Log.e(TAG, "MainActivity onCreate()-method");

        final Button button = findViewById(R.id.my_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                button.setText("uusi teksti");
                helloView=findViewById(R.id.my_textView);
                if(helloView.getVisibility()==View.INVISIBLE){
                    helloView.setVisibility(View.VISIBLE);}
               else{
                    helloView.setVisibility(View.INVISIBLE);
                }

                // Code here executes on main thread after user presses button
                Log.i(TAG, "Button clicked");
            }
        });
    }
        public void startGuess(View view) {
            Intent intent = new Intent(this, Guess.class);
            startActivity(intent);


        }



    @Override
    protected void onStart() {


        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(airplanemodeReceiver);
    }
}