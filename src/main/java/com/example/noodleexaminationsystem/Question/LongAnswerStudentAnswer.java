package com.example.noodleexaminationsystem.Question;

public class LongAnswerStudentAnswer {
    private String studentAnswer;
    private double score;
    //    -----------------------------------------------------  constructor  -----------------------------------------------------


    public LongAnswerStudentAnswer(String studentAnswer, double score) {
        this.studentAnswer = studentAnswer;
        this.score = score;
    }

    //    ---------------------------------------------------- getter/setter ------------------------------------------------------

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
