package com.example.noodleexaminationsystem.Course;

import com.example.noodleexaminationsystem.DataBase;
import com.example.noodleexaminationsystem.User.Result;
import com.example.noodleexaminationsystem.User.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonBackReference;
@JsonSerialize
@JsonDeserialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoursePlan {
    private Course course;
    @JsonBackReference
    private User teacher;
    private Exam attendedStudent;
    private LocalDate start;
    private LocalDate end;
    private String name;
    private String picturePath;
    private ArrayList<Exam> exams = new ArrayList<>();

    private CoursePlan(Course course, String name, User teacher, Exam attendedStudent, LocalDate start, String picturePath) {
        this.name = name;
        this.course = course;
        this.teacher = teacher;
        this.attendedStudent = attendedStudent;
        this.start = start;
        this.end = null;
        this.picturePath = picturePath;
    }
    public CoursePlan(){

    }

    public boolean isActive() {
        if(this.end == null){
            return true;
        }
        return false;
    }
    public static CoursePlan addCoursePlan(String courseName, String name, User teacher, LocalDate start, String picturePath){
        LocalDateTime startOfDay = start.atStartOfDay();
        LocalDate date = LocalDate.parse("2007-12-03");
        LocalTime time  = LocalTime.now();
        LocalDateTime date1 = LocalDateTime.of(date , time);
        Exam attendedStudent = Exam.createAttendedExam(startOfDay,date1);
        Course course = DataBase.getCourses().get(courseName);
        if(teacher.getTeacherCourses().get(name)!=null){
            return null;
        }
        CoursePlan coursePlan = new CoursePlan(course,name,teacher,attendedStudent,start,picturePath);
        teacher.getTeacherCourses().put(name,coursePlan);
        coursePlan.setAttendedStudent(attendedStudent);
        return coursePlan;
    }

    public  void deleteExam(Exam exam){
        this.exams.remove(exam);
    }
    public int addStudent(String username) {
        User user = DataBase.getUsers().get(username);
        //user with this username does not exist
        if(user==null)
            return 1;
        //user exists
        Result.addResult(user,this.getAttendedStudent());
        return 0;
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
    public  ArrayList<Exam> getArchivedExams(){
        ArrayList<Exam> exams = this.getExams();
        ArrayList<Exam> archivedExams = new ArrayList<>();
        for (Exam exam:exams) {
            if(exam.hasEnded())
                archivedExams.add(exam);
        }
        return archivedExams;
    }
    public  ArrayList<Exam> getActiveExams(){
        ArrayList<Exam> exams = this.getExams();
        ArrayList<Exam> activeExams = new ArrayList<>();
        for (Exam exam:exams) {
            //any exam that has not been started or has started but not ended will be shown as an active exam
            if(!exam.hasEnded())
                activeExams.add(exam);
        }
        return activeExams;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
