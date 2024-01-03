package com.example.noodleexaminationsystem.User;

import com.example.noodleexaminationsystem.Course.Exam;

public class Result {
    private User student;
    private double score;
    private Exam exam;

    public Result(User student, double score, Exam exam) {
        this.student = student;
        this.score = score;
        this.exam = exam;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
