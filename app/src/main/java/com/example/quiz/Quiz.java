package com.example.quiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Quiz {
    private Map<String, String> questionAnswerPairs;

    private List<String> cityNames;

    public Quiz() {
        questionAnswerPairs = new HashMap<>();
        createDefaultQuestionAnswerPairs();

        cityNames = new ArrayList<>();
        cityNames.add("New York");
        cityNames.add("London");
        cityNames.add("Paris");
        cityNames.add("Hyderabad");
        cityNames.add("Rajahmundry");
    }

    private void createDefaultQuestionAnswerPairs() {
        questionAnswerPairs.put("What is the capital of France?", "Paris");
        questionAnswerPairs.put("Which planet is known as the Red Planet?", "Mars");
        questionAnswerPairs.put("Which city is known as the Electronic City of India?", "Bangaluru");
        questionAnswerPairs.put("In which country, the Forbidden City is situated?", "China");
        questionAnswerPairs.put("Which city is known as the 'Gateway of India'?", "Mumbai");

        // Add more question-answer pairs as needed
    }

    public Map<String, String> getQuestionAnswerPairs() {
        return questionAnswerPairs;
    }

    public int getQuizSize() {
        return questionAnswerPairs.size();
    }

    public List<String> getCityNames() {
        return cityNames;
    }
}
