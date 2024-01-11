package com.example.noodleexaminationsystem.Question;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.DataBase;
import com.example.noodleexaminationsystem.User.User;

import java.util.ArrayList;
import java.util.Date;

public class MultipleAnswer extends MultipleChoice {
    private ArrayList<Integer> answerValues;

    private MultipleAnswer(Course course,String question, User questionDesigner, Choice countOfChoice,ArrayList<String> choices,ArrayList<Integer> answerValues) {
        super( course,question, questionDesigner,choices);
        this.answerValues = answerValues;
    }

    public static void createQuestion(String question, User questionDesigner, Course course, Choice countOfChoice,ArrayList<String> choices,ArrayList<Integer> answerValues) {
        Question question1 = new MultipleAnswer(course , question , questionDesigner , countOfChoice,choices,answerValues);
        DataBase.getQuestions().put(course , question1);
    }
//    --------------------------------------------------------      getter/setter      -----------------------------------------------------------------


    public ArrayList<Integer> getAnswerValues() {
        return answerValues;
    }

    public void setAnswerValues(ArrayList<Integer> answerValues) {
        this.answerValues = answerValues;
    }
}
