package com.example.noodleexaminationsystem.Question;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.User.User;

import java.util.ArrayList;

public abstract class MultipleChoice extends Question {
    private Course course;
    private Choice countOfChoice;
    private ArrayList<String> choices = new ArrayList<>();

    public MultipleChoice(Course course, String question, User questionDesigner, Choice countOfChoice ) {
        super(course,question, questionDesigner );
        this.countOfChoice = countOfChoice;
        this.course= course;
    }

    public Choice getCountOfChoice() {
        return countOfChoice;
    }

    public void setCountOfChoice(Choice countOfChoice) {
        this.countOfChoice = countOfChoice;
    }

    public ArrayList<String> getChoice() {
        return choices;
    }

    public void setChoice(ArrayList<String> choice) {
        this.choices = choice;
    }
}
