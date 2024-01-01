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

}
