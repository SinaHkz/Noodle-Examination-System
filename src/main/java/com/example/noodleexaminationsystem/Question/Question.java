package com.example.noodleexaminationsystem.Question;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.User.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonDeserialize
public abstract class Question {
    private Course course;
    private String question;
    private User questionDesigner;
    private long usage = 0;

    public Question() {

    }

    public Question(Course course, String question, User questionDesigner) {
        this.question = question;
        this.questionDesigner = questionDesigner;
        this.course = course;
    }
//    --------------------------------------------------------      getter/setter      -----------------------------------------------------------------

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public User getQuestionDesigner() {
        return questionDesigner;
    }

    public void setQuestionDesigner(User questionDesigner) {
        this.questionDesigner = questionDesigner;
    }

    public long getUsage() {
        return usage;
    }

    public void setUsage(long usage) {
        this.usage = usage;
    }
}
