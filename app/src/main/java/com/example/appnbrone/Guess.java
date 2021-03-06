package com.example.appnbrone;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;


public class Guess extends AppCompatActivity {
    int score;
    int highestScore;
    private static final String KEY_HS = "HighScore: ";
    private static final String MY_SCORE = "Score";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);

        findViewById(R.id.gameView).setVisibility(View.INVISIBLE);
        final ImageButton button1 = findViewById(R.id.imageButton5);
        final ImageButton button0 = findViewById(R.id.imageButton);
        final ImageButton button2 = findViewById(R.id.imageButton3);
        final ImageButton button3 = findViewById(R.id.imageButton4);

// Luodaan satunnaisluku, joka määrittää mihin dinosaurus piiloutuu
        Random rand = new Random();
        int rand_int1 = rand.nextInt(4);

        //pelin parastulos laiteen sisäisessä muistissa:
        SharedPreferences myPreferences
                = PreferenceManager.getDefaultSharedPreferences(Guess.this);

        highestScore = myPreferences.getInt("KEY_HS", 0);
        score = myPreferences.getInt("MY_SCORE", 0);

        TextView scoreText = findViewById(R.id.myScore);
        TextView highScoreText = findViewById(R.id.highScore);

        highScoreText.setText(KEY_HS + String.valueOf(highestScore));
        scoreText.setText("score: " + String.valueOf(score));







// Animaatio, kun korttia käännetään
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.roundanimation);

       // määritetään korttien klikki-ominaisuudet

        button0.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {
                                          button0.startAnimation(animation);
                                          button0.setVisibility(View.GONE);
                                          if(rand_int1 ==0) {
                                              button0.setImageResource(R.mipmap.ic_launcher_round);
                                              button0.setBackgroundColor(Color.WHITE);
                                              button0.setVisibility(View.VISIBLE);
                                              score++;
                                              scoreText.setText("score: " + String.valueOf(score));
                                              button1.setVisibility(View.INVISIBLE);
                                              button2.setVisibility(View.INVISIBLE);
                                              button3.setVisibility(View.INVISIBLE);
                                          }
                                          else {
                                              findViewById(R.id.gameView).setVisibility(View.VISIBLE);
                                              score = 0;
                                          }
                                          if(score>highestScore)
                                          {highestScore = score;
                                              highScoreText.setText(KEY_HS + String.valueOf(highestScore));}
                                      }
                                  }
        );

        button1.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {
                                          button1.startAnimation(animation);
                                          button1.setVisibility(View.GONE);
                                          if(rand_int1 ==1){
                                          button1.setImageResource(R.mipmap.ic_launcher_round);
                                          button1.setBackgroundColor(Color.WHITE);
                                          button1.setVisibility(View.VISIBLE);
                                          score++;
                                          scoreText.setText("score: " + String.valueOf(score));
                                          button0.setVisibility(View.INVISIBLE);
                                          button2.setVisibility(View.INVISIBLE);
                                          button3.setVisibility(View.INVISIBLE);}
                                          else {
                                              findViewById(R.id.gameView).setVisibility(View.VISIBLE);
                                              score = 0;
                                          }
                                          if(score>highestScore)
                                          {highestScore = score;
                                              highScoreText.setText(KEY_HS + String.valueOf(highestScore));}

                                      }
                                  }
        );
        button2.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {
                                          button2.startAnimation(animation);
                                          button2.setVisibility(View.GONE);
                                          if(rand_int1 ==2){
                                          button2.setImageResource(R.mipmap.ic_launcher_round);
                                          button2.setBackgroundColor(Color.WHITE);
                                          button2.setVisibility(View.VISIBLE);
                                              score++;
                                              scoreText.setText("score: " + String.valueOf(score));
                                              button1.setVisibility(View.INVISIBLE);
                                              button0.setVisibility(View.INVISIBLE);
                                              button3.setVisibility(View.INVISIBLE);}
                                          else {
                                              findViewById(R.id.gameView).setVisibility(View.VISIBLE);
                                              score = 0;
                                          }
                                          if(score>highestScore)
                                          {highestScore = score;
                                              highScoreText.setText(KEY_HS + String.valueOf(highestScore));}

                                      }
                                  }
        );
        button3.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {
                                          button3.startAnimation(animation);
                                          button3.setVisibility(View.GONE);
                                          if(rand_int1 ==3){
                                          button3.setImageResource(R.mipmap.ic_launcher_round);
                                          button3.setBackgroundColor(Color.WHITE);
                                          button3.setVisibility(View.VISIBLE);
                                              score++;
                                              scoreText.setText("score: " + String.valueOf(score));
                                              button1.setVisibility(View.INVISIBLE);
                                              button2.setVisibility(View.INVISIBLE);
                                              button0.setVisibility(View.INVISIBLE);}
                                          else {
                                              findViewById(R.id.gameView).setVisibility(View.VISIBLE);
                                              score = 0;
                                          }
                                          if(score>highestScore)
                                          {highestScore = score;
                                              highScoreText.setText(KEY_HS + String.valueOf(highestScore));}

                                      }
                                  }
        );
    }


    public void refresh(View view)
    {

        SharedPreferences myPreferences
                = PreferenceManager.getDefaultSharedPreferences(Guess.this);
        SharedPreferences.Editor editor = myPreferences.edit();
        editor.putInt("KEY_HS",highestScore);
        editor.apply();

        editor.putInt("MY_SCORE", score);
        editor.apply();


        finish();
        startActivity(getIntent());
    }



}