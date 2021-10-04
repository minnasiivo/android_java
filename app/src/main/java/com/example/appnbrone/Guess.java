package com.example.appnbrone;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import java.util.Random;


public class Guess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);


        Random rand = new Random();
        int rand_int1 = rand.nextInt(4);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.roundanimation);
        final ImageButton button0 = findViewById(R.id.imageButton);
        button0.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {
                                          button0.startAnimation(animation);
                                          button0.setVisibility(View.GONE);
                                          if(rand_int1 ==0){
                                          button0.setImageResource(R.mipmap.ic_launcher_round);
                                          button0.setBackgroundColor(Color.WHITE);
                                          button0.setVisibility(View.VISIBLE);}
                                      }
                                  }
        );
        final ImageButton button1 = findViewById(R.id.imageButton5);
        button1.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {
                                          button1.startAnimation(animation);
                                          button1.setVisibility(View.GONE);
                                          if(rand_int1 ==1){
                                          button1.setImageResource(R.mipmap.ic_launcher_round);
                                          button1.setBackgroundColor(Color.WHITE);
                                          button1.setVisibility(View.VISIBLE);}
                                      }
                                  }
        ); final ImageButton button2 = findViewById(R.id.imageButton3);
        button2.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {
                                          button2.startAnimation(animation);
                                          button2.setVisibility(View.GONE);
                                          if(rand_int1 ==2){
                                          button2.setImageResource(R.mipmap.ic_launcher_round);
                                          button2.setBackgroundColor(Color.WHITE);
                                          button2.setVisibility(View.VISIBLE);}
                                      }
                                  }
        ); final ImageButton button3 = findViewById(R.id.imageButton4);
        button3.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {
                                          button3.startAnimation(animation);
                                          button3.setVisibility(View.GONE);
                                          if(rand_int1 ==3){
                                          button3.setImageResource(R.mipmap.ic_launcher_round);
                                          button3.setBackgroundColor(Color.WHITE);
                                          button3.setVisibility(View.VISIBLE);}
                                      }
                                  }
        );
    }


    public void refresh(View view)
    {
        finish();
        startActivity(getIntent());
    }


    public static void main(String args[])
    {
        // create instance of Random class
        Random rand = new Random();

        // Generate random integers in range 0 to 3
        int rand_int1 = rand.nextInt(4);
    }
}