package com.example.noodleexaminationsystem.Course;

import com.example.noodleexaminationsystem.DataBase;
import com.example.noodleexaminationsystem.User.Result;
import com.example.noodleexaminationsystem.User.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class CoursePlan {
    private Course course;
    private User teacher;
    private Exam attendedStudent;
    private LocalDate start;
    private LocalDate end;
    private String name;
    private String picturePath;
    private ArrayList<Exam> exams = new ArrayList<>();

    public CoursePlan(Course course, String name, User teacher, Exam attendedStudent, LocalDate start, LocalDate end, String picturePath) {
        this.name = name;
        this.course = course;
        this.teacher = teacher;
        this.attendedStudent = attendedStudent;
        this.start = start;
        this.end = end;
        this.picturePath = picturePath;
    }

    public boolean isActive(LocalDate date) {
        if (date.isAfter(this.start) && date.isBefore(this.end))
            return true;
        else
            return false;
    }
    public  void deleteExam(Exam exam){
        this.exams.remove(exam);
    }
    public int addStudent(String username) {
        for (User user : DataBase.getUsers().values()) {
            if (user.getUsername().equals(username)) {
                Result result = new Result(user, 0.0, attendedStudent); //Dont sure about the attended student which is a exam
                attendedStudent.getResults().add(result);
                return 0;
            }
        }
        return 1;//error find
    }

    public void removeStudent(String user) {
        this.getAttendedStudent().removeUser(user);
    }

    public ArrayList<User> getStudent() {
        ArrayList<User> students = new ArrayList<>();
        for (Result result : attendedStudent.getResults()) {
            User user = result.getStudent();
            students.add(user);
        }
        return students;
    }

    public void createNewExam(String title) {
        Exam exam = new Exam(title);
        exams.add(exam);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addExam(Exam exam) {
        exams.add(exam);
    }

    public Exam getAttendedStudent() {
        return attendedStudent;
    }

    public void setAttendedStudent(Exam attendedStudent) {
        this.attendedStudent = attendedStudent;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public ArrayList<Exam> getExams() {
        return exams;
    }

    public void setExams(ArrayList<Exam> exams) {
        this.exams = exams;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
}
