package com.example.noodleexaminationsystem.Question;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.User.User;

import java.util.ArrayList;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonDeserialize

public abstract class MultipleChoice extends Question {
    private Choice countOfChoice;
    private ArrayList<String> choices;
    public MultipleChoice(){

    }

    public MultipleChoice(Course course, String question, User questionDesigner,ArrayList<String> choices) {
        super(course,question, questionDesigner );
        this.countOfChoice = Choice.getByValue(choices.size());
        this.choices = choices;
    }
    //    --------------------------------------------------------      getter/setter      -----------------------------------------------------------------

    public Choice getCountOfChoice() {
        return countOfChoice;
    }

    public void setCountOfChoice(Choice countOfChoice) {
        this.countOfChoice = countOfChoice;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<String> choices) {
        this.choices = choices;
    }
}
