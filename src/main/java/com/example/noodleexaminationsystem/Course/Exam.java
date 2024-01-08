package com.example.noodleexaminationsystem.Course;

import com.example.noodleexaminationsystem.Question.Question;
import com.example.noodleexaminationsystem.User.Result;
import com.example.noodleexaminationsystem.User.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Exam {
    private String examTitle;
    private LocalDateTime TimeStart;
    private LocalDateTime TimeEnd;
    private ArrayList<Question> questions = new ArrayList();
    private ArrayList<Result> results = new ArrayList<>();

    private Exam(String examTitle, LocalDateTime timeStart, LocalDateTime timeEnd) {
        this.examTitle = examTitle;
        TimeStart = timeStart;
        TimeEnd = timeEnd;
    }

    public static Exam createExam(String examTitle, LocalDateTime timeStart, LocalDateTime timeEnd) {

        Exam exam=new Exam(examTitle , timeStart , timeEnd);
        return exam;
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

    public void createExam(Question question){
        questions.add(question);
        question.addusage();
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
    public void removeUser(String user){
        for (Result result : results){
            if (result.getStudent().getUsername().equals(user))
                results.remove(result);
        }
    }
    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }
}
