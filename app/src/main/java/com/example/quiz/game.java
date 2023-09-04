package com.example.quiz;
;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class game extends AppCompatActivity {

    TextView tv, ty, sc, opA, opB, opC, opD;
    CardView oA, oB, oC, oD;
    Map<String, String> questionAnswerPairs;
    Quiz quiz; // Declare the quiz object
    private Score score;
    List<TextView> optionViews;
    private List<String> displayedQuestions;

    public String myString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        quiz = new Quiz();
        score = new Score(quiz.getQuestionAnswerPairs()); // Initialize Score with the question-answer pairs


        tv = findViewById(R.id.tv);
        ty = findViewById(R.id.ty);
        sc = findViewById(R.id.sc);

        opA = findViewById(R.id.opA);
        opB = findViewById(R.id.opB);
        opC = findViewById(R.id.opC);
        opD = findViewById(R.id.opD);

        oA = findViewById(R.id.optionA);
        oB = findViewById(R.id.optionB);
        oC = findViewById(R.id.optionC);
        oD = findViewById(R.id.optionD);

        Intent intent = getIntent();
        String selectedGame = intent.getStringExtra("typegame");
        ty.setText("Selected Game type:\n\t\t\t\t\f"+selectedGame);


        myString = selectedGame;
        displayedQuestions = new ArrayList<>();

        optionViews = new ArrayList<>(); // Initialize optionViews here

        optionViews.add(opA);
        optionViews.add(opB);
        optionViews.add(opC);
        optionViews.add(opD);

        // Initialize the quiz object and retrieve the question-answer pairs from Quiz class or your data source.
        quiz = new Quiz();
        questionAnswerPairs = quiz.getQuestionAnswerPairs();

        // Set up click listeners for CardViews
        oA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer((CardView) v, opA.getText().toString());
            }
        });

        oB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer((CardView) v, opB.getText().toString());
            }
        });

        oC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer((CardView) v, opC.getText().toString());
            }
        });

        oD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer((CardView) v, opD.getText().toString());
            }
        });

        // Set the initial question and answer.
        setRandomQuestionAndAnswer();


    }

    private void setRandomQuestionAndAnswer() {
        // Check if all questions have been displayed.
        if (displayedQuestions.size() == questionAnswerPairs.size()) {
            // Handle the case when all questions have been displayed.
            // You can show a message or perform other actions here.
            Log.d("GameDebug","is all displayed" + displayedQuestions.size());
            Log.d("GameDebug","is all displayed" + displayedQuestions.size());
            main();
        }

        // Get a random question that has not been displayed before.
        String randomQuestion = null;

        List<String> questionKeys = new ArrayList<>(questionAnswerPairs.keySet());
        Collections.shuffle(questionKeys);

        for (String questionKey : questionKeys) {
            if (!displayedQuestions.contains(questionKey)) {
                randomQuestion = questionKey;
                displayedQuestions.add(questionKey);
                break;
            }
        }

        // Check if a valid question was found.
        if (randomQuestion != null) {
            // Get the correct answer for the selected question.
            String randomAnswer = questionAnswerPairs.get(randomQuestion);

            // Get a list of random incorrect options (city names).
            List<String> incorrectOptions = getRandomCityNames(randomAnswer);

            // Create a list of options that includes the correct answer and incorrect options.
            List<String> options = new ArrayList<>(incorrectOptions);
            options.add(randomAnswer); // Add the correct answer to the options.
            Collections.shuffle(options);

            // Set the selected question.
            tv.setText(randomQuestion);

            // Set the shuffled options in the shuffled order.
            for (int i = 0; i < optionViews.size(); i++) {
                TextView optionTextView = optionViews.get(i);
                optionTextView.setText(options.get(i));
            }
        } else {
            // Handle the case when all questions have been displayed.
            // You can show a message or perform other actions here.
        }
    }

    private List<String> getRandomCityNames(String correctAnswer) {
        // Get a list of city names excluding the correct answer.
        List<String> availableCityNames = new ArrayList<>(quiz.getCityNames());
        availableCityNames.remove(correctAnswer);

        // Shuffle the list of city names and select three random ones.
        Collections.shuffle(availableCityNames);
        return availableCityNames.subList(0, 3);
    }

    private void checkAnswer(CardView cardView, String selectedAnswer) {
        // Get the CardView's TextView.
        TextView cardViewTextView = (TextView) cardView.getChildAt(0);

        // Get the current question (from TextView tv).
        String currentQuestion = tv.getText().toString();


        // Call the updateScore method of the Score class and pass the selected answer and question.
        score.updateScore(selectedAnswer, currentQuestion,myString);

        // Update the score TextView (sc).
        sc.setText("Score: " + score.getScore()); // Update the score TextView

        // Refresh the question and answer.
        setRandomQuestionAndAnswer();
    }

    private void main(){
        String sco = sc.getText().toString();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Congratulations ")
        .setMessage("You've completed Quiz. \n Your " + sco + "\n Would you like to return to Main Page or Quit")
        .setPositiveButton("Main Page", new DialogInterface.OnClickListener() {
          @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle OK button click (if needed)
                getma();
            }
        })
        .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            // Handle Cancel button click (if needed)
             dialog.dismiss();
             finishAffinity();
          }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public void getma(){
        Intent Inte = new Intent(this,MainActivity.class);
        startActivity(Inte);
    }

}
