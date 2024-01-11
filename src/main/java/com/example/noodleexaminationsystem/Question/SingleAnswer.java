package com.example.noodleexaminationsystem.Question;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.DataBase;
import com.example.noodleexaminationsystem.User.User;

import java.util.ArrayList;

public class SingleAnswer extends MultipleChoice {
    private int answerValue;

    public SingleAnswer(Course course, String question, User questionDesigner, Choice countOfChoice, int answerValue,ArrayList<String> choices) {
        super(course, question, questionDesigner,choices);
        this.answerValue = answerValue;
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
