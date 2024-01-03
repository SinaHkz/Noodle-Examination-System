package com.example.noodleexaminationsystem.Question;

import com.example.noodleexaminationsystem.User.User;

import java.util.ArrayList;

public class MultipleAnswer extends MultipleChoice {
    private ArrayList<Integer> answerValue = new ArrayList<>();

    public MultipleAnswer(String question, User questionDesigner, Choice countOfChoice) {
        super( question, questionDesigner, countOfChoice);
    }
    public ArrayList<Integer> getAnswerValue() {
        return answerValue;
    }

    public void setAnswerValue(ArrayList<Integer> answerValue) {
        this.answerValue = answerValue;
    }


}
