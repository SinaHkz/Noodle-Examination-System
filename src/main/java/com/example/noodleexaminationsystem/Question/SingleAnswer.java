package com.example.noodleexaminationsystem.Question;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.DataBase;
import com.example.noodleexaminationsystem.User.User;

public class SingleAnswer extends MultipleChoice {
    private int answerValue;

    public SingleAnswer(Course course,String question, User questionDesigner, Choice countOfChoice, int answerValue) {
        super(course, question, questionDesigner, countOfChoice);
        this.answerValue = answerValue;
    }

    @Override
    public void createQuestion(String question, User questionDesigner, Course course) {
        Question question1 = new SingleAnswer(course , question , questionDesigner , getCountOfChoice() , answerValue);
        DataBase.getQuestions().put(course , question1);

    }

    @Override
    public void deleteQuestion() {
        DataBase.getQuestions().remove(getCourse());
    }

    public int getAnswerValue() {
        return answerValue;
    }

    public void setAnswerValue(int answerValue) {
        this.answerValue = answerValue;
    }

}
