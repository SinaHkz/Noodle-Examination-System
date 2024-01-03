package com.example.noodleexaminationsystem.Course;

import com.example.noodleexaminationsystem.Question.Question;
import com.example.noodleexaminationsystem.User.Result;

import java.util.ArrayList;

public class Exam {
    private String examTitle;
    private ArrayList<Question> questions = new ArrayList();
    private ArrayList<Result> results = new ArrayList<>();

    public Exam(String examTitle) {
        this.examTitle = examTitle;
    }

    public String getExamTitle() {
        return examTitle;
    }
    public void addQuestion(Question question){
        questions.add(question);
    }
    public void addResult(Result result){
        results.add(result);
    }
    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }
}
