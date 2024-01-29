package com.example.noodleexaminationsystem.Course;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.example.noodleexaminationsystem.Question.Question;
import com.example.noodleexaminationsystem.User.Result;
import com.example.noodleexaminationsystem.User.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Exam {
    private String examTitle;
    private LocalDateTime TimeStart;
    private LocalDateTime TimeEnd;
    private ArrayList<Question> questions = new ArrayList();
    private ArrayList<Result> results = new ArrayList<>();

    public Exam() {

    }
    //______________________________________________________ constructor _________________________________________________________

    private Exam(String examTitle, LocalDateTime timeStart, LocalDateTime timeEnd) {
        this.examTitle = examTitle;
        this.TimeStart = timeStart;
        this.TimeEnd = timeEnd;
    }
    //______________________________________________________ getter/setter _________________________________________________________

    public boolean hasStarted() {
        if (LocalDateTime.now().isAfter(this.getTimeStart()))
            return true;
        return false;
    }

    public boolean hasEnded() {
        if (LocalDateTime.now().isAfter(this.getTimeEnd()))
            return true;
        return false;
    }

    public static Exam createExam(CoursePlan coursePlan, String examTitle, LocalDateTime timeStart, LocalDateTime timeEnd) {
        Exam exam = new Exam(examTitle, timeStart, timeEnd);
        coursePlan.getExams().add(exam);
        return exam;
    }

    public static Exam createAttendedExam(LocalDateTime timeStart, LocalDateTime timeEnd) {
        Exam exam = new Exam("attendedExam", timeStart, timeEnd);
        return exam;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void addResult(Result result) {
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

    public LocalDateTime getTimeStart() {
        return TimeStart;
    }

    public void setTimeStart(LocalDateTime timeStart) {
        TimeStart = timeStart;
    }

    public LocalDateTime getTimeEnd() {
        return TimeEnd;
    }

    public void setTimeEnd(LocalDateTime timeEnd) {
        TimeEnd = timeEnd;
    }

    //______________________________________________________ methods _________________________________________________________
//
    public boolean isActive() {
        if ((LocalDateTime.now().isBefore(this.getTimeEnd())) && (LocalDateTime.now().isAfter(this.getTimeStart())))
            return true;
        return false;
    }

    public void removeUser(User user) {
        for (Result result : this.results) {
            if (result.getStudent() == user) {
                this.results.remove(result);
                user.getResults().remove(result);
                break;
            }
        }
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }

}
