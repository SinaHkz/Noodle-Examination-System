package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.Course.CoursePlan;
import com.example.noodleexaminationsystem.Question.Question;
import com.example.noodleexaminationsystem.User.User;

import java.util.ArrayList;

public class DataBase {
    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<CoursePlan> coursePlans = new ArrayList<>();
    static ArrayList<Course> courses = new ArrayList<>();
    static ArrayList<Question> questions = new ArrayList<>();


    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<User> users) {
        DataBase.users = users;
    }

    public static ArrayList<CoursePlan> getCoursePlans() {
        return coursePlans;
    }

    public static void setCoursePlans(ArrayList<CoursePlan> coursePlans) {
        DataBase.coursePlans = coursePlans;
    }

    public static ArrayList<Course> getCourses() {
        return courses;
    }

    public static void setCourses(ArrayList<Course> courses) {
        DataBase.courses = courses;
    }

    public static ArrayList<Question> getQuestions() {
        return questions;
    }

    public static void setQuestions(ArrayList<Question> questions) {
        DataBase.questions = questions;
    }
}
