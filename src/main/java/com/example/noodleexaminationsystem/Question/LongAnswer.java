package com.example.noodleexaminationsystem.Question;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.DataBase;
import com.example.noodleexaminationsystem.User.User;

public class LongAnswer extends Question {
    private String answer;

    public LongAnswer(Course course, String question, User questionDesigner, String answer) {
        super(course, question, questionDesigner );
        this.answer = answer;
    }


    public void createQuestion(String question, User questionDesigner, Course course) {
        Question question1 = new LongAnswer(course , question , questionDesigner , answer);
        DataBase.getQuestions().put(course , question1);
    }
//    --------------------------------------------------------      getter/setter      -----------------------------------------------------------------

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
