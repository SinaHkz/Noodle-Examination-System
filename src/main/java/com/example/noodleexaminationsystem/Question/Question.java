package com.example.noodleexaminationsystem.Question;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.User.User;

public abstract class Question {
    private Course course;
    private long id;
    private String question;
    private User questionDesigner;
    private long usage = 0 ;

    public Question( Course course,String question, User questionDesigner ) {
        this.question = question;
        this.questionDesigner = questionDesigner;
        this.course = course;
    }

    public long getUsage() {
        return usage;
    }
    public void addusage(){
         usage++;
    }

    public void setUsage(long usage) {
        this.usage = usage;
    }

    public abstract void createQuestion(String question, User questionDesigner, Course course);
    public abstract void deleteQuestion();

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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
