package com.example.noodleexaminationsystem.Question;

import com.example.noodleexaminationsystem.User.User;

public class LongAnswer extends Question {
    private String answer;

    public LongAnswer(long id, String question, User questionDesigner, String answer) {
        super(id, question, questionDesigner);
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
