package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.Course.CoursePlan;
import com.example.noodleexaminationsystem.Question.Question;
import com.example.noodleexaminationsystem.TrieTree.Trie;
import com.example.noodleexaminationsystem.User.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataBase {
    static Map<String, User> users = new HashMap<>();
    static Map<String, CoursePlan> coursePlans = new HashMap<>();
    static Map<String, Course> courses = new HashMap<>();
    public static Trie usernameTrie = new Trie();
    static Map<Course, ArrayList<Question>> questions = new HashMap<>();



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

    public static Map<String, Course> getCourses() {
        return courses;
    }

    public static void setCourses(Map<String, Course> courses) {
        DataBase.courses = courses;
    }

    public static Map<Course, ArrayList<Question>> getQuestions() {
        return questions;
    }

    public static void setQuestions(Map<Course, ArrayList<Question>> questions) {
        DataBase.questions = questions;
    }

    public DataBase() {
    }

    //----------------------------------------------------------------------
}