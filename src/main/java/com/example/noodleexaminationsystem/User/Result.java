package com.example.noodleexaminationsystem.User;

import com.example.noodleexaminationsystem.Course.CoursePlan;
import com.example.noodleexaminationsystem.Course.Exam;
import com.example.noodleexaminationsystem.Question.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Result {
    private User student;
    private double score = 0;
    private Exam exam;

    private Map<Question, Object> answers = new HashMap<>();
    public Result(){

    }

    private Result(User student, Exam exam) {
        this.student = student;
        this.exam = exam;
        ArrayList<Question> questions = exam.getQuestions();
        for (Question question:questions) {
            answers.put(question, null);
        }
    }
    public static Result addResult(User student,Exam exam){
        Result result = new Result(student,exam);
        exam.getResults().add(result);
        student.getResults().add(result);
        return result;
    }
    //    -------------------------------------------------------------  getter/setter  ------------------------------------------------------------

    public Map<Question, Object> getAnswers() {
        return answers;
    }

    public void setAnswers(HashMap<Question, Object> answers) {
        this.answers = answers;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
