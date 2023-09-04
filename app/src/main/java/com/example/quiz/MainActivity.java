package com.example.quiz;


import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    Button Eas, Cha; // Buttons for Easy and Challenge Quiz.
    FloatingActionButton fabAbout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Eas = findViewById(R.id.easqui);
        Cha = findViewById(R.id.chaqui);

        fabAbout = findViewById(R.id.fabAbout);

        Eas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQuizTypeDialog("normal");
            }
        });

        Cha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQuizTypeDialog("Challenge");
            }
        });

        fabAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startabout();
            }
        });
    }

    private void showQuizTypeDialog(final String quizType){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(quizType.equals("normal")){
            builder.setTitle("⚠️ Warning about Quiz Type ⚠️")
                    .setMessage("By clicking Easy Quiz, you'll get a score based on your correct answer. If you want a more challenging experience, select Challenge Quiz where your score will be reduced for every wrong answer.")
                    .setPositiveButton("Go ahead", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startgame("Normal");

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss(); //Shows the Home Page.
                        }
                    });
        } else if (quizType.equals("Challenge")) {
            builder.setTitle("⚠️ Warning about Quiz Type ⚠️")
                    .setMessage("By clicking Challenge Quiz, your score will be reduced for every wrong answer. If you want a more relaxed and easy experience, select Easy Quiz where you'll get a score based on your correct answer.")
                    .setPositiveButton("Go ahead", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            String myString = "Challenge";
                            // Create an instance of the Score class and pass the String
                            Score score = new Score(myString);
                            startgame("Challenge");

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();// Shows the Home Page.
                        }
                    });

        }
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void startabout(){
        Intent i = new Intent(MainActivity.this, About.class);
        startActivity(i);
    }

    private void startgame(String j) {
        Intent inte = new Intent(this, game.class);
        inte.putExtra("typegame", j);
        startActivity(inte);

    }
}