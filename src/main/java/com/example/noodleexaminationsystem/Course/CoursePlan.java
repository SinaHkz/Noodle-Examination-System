package com.example.noodleexaminationsystem.Course;

import com.example.noodleexaminationsystem.User.User;
import java.util.ArrayList;
import java.util.Date;

public class CoursePlan {
    private Course course;
    private User teacher;
    private Exam attendedStudent;
    private Date start;
    private Date end;
    private String name;
    private ArrayList<Exam> exams = new ArrayList<>();

    public CoursePlan(Course course,String name, User teacher, Exam attendedStudent, Date start, Date end) {
        this.name=name;
        this.course = course;
        this.teacher = teacher;
        this.attendedStudent = attendedStudent;
        this.start = start;
        this.end = end;
    }
    //when person login find courses
    //teacher login find courses
    //
    public boolean isAcitve(Date date){
        if(date.after(start) && date.before(end))
            return true;
        else
            return false;
    }
    public void createNewExam(String title){
        Exam exam = new Exam(title);
        exams.add(exam);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void addExam(Exam exam){
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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public ArrayList<Exam> getExams() {
        return exams;
    }

    public void setExams(ArrayList<Exam> exams) {
        this.exams = exams;
    }
}
