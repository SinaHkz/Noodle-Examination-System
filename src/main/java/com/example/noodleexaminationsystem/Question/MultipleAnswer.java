package com.example.noodleexaminationsystem.Question;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.DataBase;
import com.example.noodleexaminationsystem.User.User;

import java.util.ArrayList;
import java.util.Date;

public class MultipleAnswer extends MultipleChoice {
    private ArrayList<Integer> answerValue = new ArrayList<>();

    public MultipleAnswer(Course course,String question, User questionDesigner, Choice countOfChoice) {
        super( course,question, questionDesigner, countOfChoice);
    }
    public ArrayList<Integer> getAnswerValue() {
        return answerValue;
    }

    @Override
    public void createQuestion(String question, User questionDesigner, Course course) {
        Question question1 = new MultipleAnswer(course , question , questionDesigner , getCountOfChoice());
        DataBase.getQuestions().put(course , question1);
    }

    @Override
    public void deleteQuestion() {
        DataBase.getQuestions().remove(this.getCourse());
    }

    public void setAnswerValue(ArrayList<Integer> answerValue) {
        this.answerValue = answerValue;
    }


}
