package com.example.appnbrone;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;


public class Guess extends AppCompatActivity {

    private static final String KEY_HS = "HighScore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);

        findViewById(R.id.gameView).setVisibility(View.INVISIBLE);


// Luodaan satunnaisluku, joka määrittää mihin dinosaurus piiloutuu
        Random rand = new Random();
        int rand_int1 = rand.nextInt(4);

        //pelin parastulos laiteen sisäisessä muistissa:

        final int score;
        SharedPreferences myPreferences
                = PreferenceManager.getDefaultSharedPreferences(Guess.this);
        SharedPreferences.Editor myEditor = myPreferences.edit();
        myEditor.putInt("KEY_HS",0);
        myEditor.commit();
        int highScore = myPreferences.getInt("KEY_HS", 0);


// Animaatio, kun korttia käännetään
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.roundanimation);

       // määritetään korttien klikki-ominaisuudet
        final ImageButton button0 = findViewById(R.id.imageButton);
        button0.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {
                                          button0.startAnimation(animation);
                                          button0.setVisibility(View.GONE);
                                          if(rand_int1 ==0){
                                          button0.setImageResource(R.mipmap.ic_launcher_round);
                                          button0.setBackgroundColor(Color.WHITE);
                                          button0.setVisibility(View.VISIBLE);}
                                          else {
                                              findViewById(R.id.gameView).setVisibility(View.VISIBLE);
                                          }
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



}