    package com.example.quiz;

    import android.util.Log;
    import java.util.Map;

    public class Score {
        private int score;
        private Map<String, String> questionAnswerPairs;
        public String myString;


        public Score(String myString) {
            this.myString = myString;
        }

        public Score(Map<String, String> questionAnswerPairs) {
            this.score = 0; // Initialize the score to 0
            this.questionAnswerPairs = questionAnswerPairs;

        }


        public int getScore() {
            return score;
        }

        public void increaseScore() {
            score += 5;
        }

        public void decreaseScore() {
            score -= 5;


        }

        public void resetScore() {
            score = 0;
        }

        public void updateScore(String selectedAnswer, String currentQuestion, String myText) {


            // Check if the currentQuestion exists in the question-answer pairs map.
            if (questionAnswerPairs.containsKey(currentQuestion)) {
                // Get the correct answer from the question-answer pairs.
                String correctAnswer = questionAnswerPairs.get(currentQuestion);

                // Check if the selected answer matches the correct answer.
                if (selectedAnswer.equals(correctAnswer)) {
                    correct();
                } else {
                    incorrect(myText);

                }
            }
        }

        private void correct() {
            // Implement logic for a correct answer, such as increasing the score.
            increaseScore();
        }

        public void incorrect(String myst) {
            if (myst != null && myst.equals("Challenge")) {
                decreaseScore();

            } else {
                Log.d("ScoreDebug", "Score not decreased. Current Score: " + myst);
            }
        }

    }



