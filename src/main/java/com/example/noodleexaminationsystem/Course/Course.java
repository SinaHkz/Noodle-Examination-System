package com.example.noodleexaminationsystem.Course;

import java.util.ArrayList;

public class Course {
    private String course;
    private ArrayList<CoursePlan> coursePlans = new ArrayList<>();

    public Course(String course) {
        this.course = course;
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
