package com.example.noodleexaminationsystem.Question;

import com.example.noodleexaminationsystem.User.User;

public abstract class Question {
    private long id;
    private String question;
    private User questionDesigner;

    public Question( String question, User questionDesigner) {
        this.question = question;
        this.questionDesigner = questionDesigner;
    }


    public User getQuestionDesigner() {
        return questionDesigner;
    }

    public void setQuestionDesigner(User questionDesigner) {
        this.questionDesigner = questionDesigner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
