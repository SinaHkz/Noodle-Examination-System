package com.example.noodleexaminationsystem.Question;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.DataBase;
import com.example.noodleexaminationsystem.User.User;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonDeserialize

public class SingleAnswer extends MultipleChoice {
    private int answerValue;

    public SingleAnswer(Course course, String question, User questionDesigner, Choice countOfChoice, int answerValue,ArrayList<String> choices) {
        super(course, question, questionDesigner,choices);
        this.answerValue = answerValue;
    }
    public SingleAnswer(){

    }



    public static void createQuestion(String question, User questionDesigner, Course course,Choice countOfChoice,int answerValue,ArrayList<String> choices) {
        Question question1 = new SingleAnswer(course , question , questionDesigner , countOfChoice , answerValue,choices);
        DataBase.getQuestions().put(course , question1);
    }
//    --------------------------------------------------------      getter/setter      -----------------------------------------------------------------


    public int getAnswerValue() {
        return answerValue;
    }

    public void setAnswerValue(int answerValue) {
        this.answerValue = answerValue;
    }
}
