package com.example.noodleexaminationsystem.Question;

import com.example.noodleexaminationsystem.User.User;

public class SingleAnswer extends MultipleChoice {
    private int answerValue;

    public SingleAnswer(long id, String question, User questionDesigner, Choice countOfChoice, int answerValue) {
        super(id, question, questionDesigner, countOfChoice);
        this.answerValue = answerValue;
    }

    public int getAnswerValue() {
        return answerValue;
    }

    public void setAnswerValue(int answerValue) {
        this.answerValue = answerValue;
    }
}
