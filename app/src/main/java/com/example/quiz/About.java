package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class About extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        tv = findViewById(R.id.tv);
        tv.setText("Introducing MDA Raju's CodSoft Internship Creation: Quiz App\n" +
                "\n" +
                "The Quiz App, a creation of MDA Raju during the CodSoft Internship, offers an engaging user experience. It presents two distinct quiz modes:\n" +
                "→ The first being a fun and standard quiz, where points are awarded for correct answers.\n" +
                "→ The second mode, the Challenge, adopts a different approach by deducting points for incorrect answers.\n"+
                "Users are provided with their scores upon quiz completion, enhancing the interactive nature of the app.\n"+
                "Best regards,\n" +
                "MDA Raju ");
    }
}