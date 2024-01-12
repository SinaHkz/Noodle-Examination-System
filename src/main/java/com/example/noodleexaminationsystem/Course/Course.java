package com.example.noodleexaminationsystem.Course;

import com.example.noodleexaminationsystem.DataBase;

import java.util.ArrayList;
//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//
//@JsonSerialize
//@JsonDeserialize
public class Course {
    private String course;
    private ArrayList<CoursePlan> coursePlans = new ArrayList<>();

    private Course(String course) {
        this.course = course;
    }

    public static Course addCourse(String courseName) {
        if (!DataBase.getCourses().containsKey(courseName)) {
            Course course1 = new Course(courseName);
            DataBase.getCourses().put(courseName, course1);
            return course1;
        }
        return null;
    }
    public Course(){

    }

    public boolean deleteCourse() {
        DataBase.getCourses().remove(this.course, this);
        return true;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public ArrayList<CoursePlan> getCoursePlans() {
        return coursePlans;
    }

    public void setCoursePlans(ArrayList<CoursePlan> coursePlans) {
        this.coursePlans = coursePlans;
    }
}
