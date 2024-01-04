package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.Course.CoursePlan;
import com.example.noodleexaminationsystem.Question.Question;
import com.example.noodleexaminationsystem.User.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataBase {
    static Map<String , User> users = new HashMap<>();
    static Map<String , CoursePlan> coursePlans = new HashMap<>();
    static ArrayList<Course> courses = new ArrayList<>();
    static Map<Course,Question> questions = new HashMap<>();

    public static void setUsers(Map<String, User> users) {
        DataBase.users = users;
    }

    public static Map<String, User> getUsers() {
        return users;
    }

    public static Map<String, CoursePlan> getCoursePlans() {
        return coursePlans;
    }

    public static void setCoursePlans(Map<String, CoursePlan> coursePlans) {
        DataBase.coursePlans = coursePlans;
    }

    public static ArrayList<Course> getCourses() {
        return courses;
    }

    public static void setCourses(ArrayList<Course> courses) {
        DataBase.courses = courses;
    }

    public static Map<Course, Question> getQuestions() {
        return questions;
    }

    public static void setQuestions(Map<Course, Question> questions) {
        DataBase.questions = questions;
    }
}
