package com.example.noodleexaminationsystem.Question;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.DataBase;
import com.example.noodleexaminationsystem.User.User;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;

public class LongAnswer extends Question {
    private String answer;

    public LongAnswer(Course course, String question, User questionDesigner, String answer) {
        super(course, question, questionDesigner );
        this.answer = answer;
    }
    public LongAnswer(){

    }



    public static Question createQuestion(String question, User questionDesigner, Course course,String answer) {
        Question question1 = new LongAnswer(course , question , questionDesigner , answer);
        DataBase.getQuestions().get(course).add(question1);
        return question1;
    }
//    --------------------------------------------------------      getter/setter      -----------------------------------------------------------------

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
