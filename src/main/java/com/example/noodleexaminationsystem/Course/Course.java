package com.example.noodleexaminationsystem.Course;
import com.example.noodleexaminationsystem.DataBase;
import java.util.ArrayList;
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
            DataBase.getQuestions().put(course1,new ArrayList<>());
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
