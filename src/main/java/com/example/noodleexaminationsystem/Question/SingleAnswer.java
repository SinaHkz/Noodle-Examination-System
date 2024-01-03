package com.example.noodleexaminationsystem.Question;

import com.example.noodleexaminationsystem.User.User;

public class SingleAnswer extends MultipleChoice {
    private int answerValue;

    public SingleAnswer( String question, User questionDesigner, Choice countOfChoice, int answerValue) {
        super( question, questionDesigner, countOfChoice);
        this.answerValue = answerValue;
    }

    public int getAnswerValue() {
        return answerValue;
    }

    public void setAnswerValue(int answerValue) {
        this.answerValue = answerValue;
    }

}
