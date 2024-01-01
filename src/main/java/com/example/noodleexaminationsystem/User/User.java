package com.example.noodleexaminationsystem.User;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.Course.CoursePlan;
import com.example.noodleexaminationsystem.Course.Exam;
import com.example.noodleexaminationsystem.DataBase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class User {
    private String username;
    private String password;
    private String name;
    private String lastName;
    private String email;
    private String picturePath;
    private LocalDate dob;
    private ArrayList<Result> results = new ArrayList<>();
    private ArrayList<CoursePlan> teacherCourses = new ArrayList<>();

    public User(String username, String password, String name, String lastName, String email, String picturePath, LocalDate dob) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.picturePath = picturePath;
        this.dob = dob;
    }
    public User SignUp(String username ,String password ,String name ,String lastName ,String email ,String picturePath ,LocalDate dob){
        User user1 = new User(username, password,name,lastName,email,picturePath,dob);
        DataBase.getUsers().add(user1);
        return user1;
    }
    public User login(String username , String password){
        for (User user : DataBase.getUsers()){
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
    public CoursePlan createCoursePlan(String name , Course course , Date start , Date end , User teacher , Exam attendedStudent){
        CoursePlan coursePlan=new CoursePlan(course,name,teacher,attendedStudent,start,end);
        return coursePlan;
    }

    public ArrayList<CoursePlan> coursePlanArrayList(CoursePlan coursePlan){
        ArrayList<CoursePlan> coursePlans=getTeacherCourses();
        coursePlans.add(coursePlan);
        return coursePlans;
    }

    public int resetpassword(String password,String newpassword){
        if(this.password.equals(password)){
            setPassword(newpassword);
            return 1;
        }else
            return 0;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }

    public ArrayList<CoursePlan> getTeacherCourses() {
        return teacherCourses;
    }

    public void setTeacherCourses(ArrayList<CoursePlan> teacherCourses) {
        this.teacherCourses = teacherCourses;
    }
}
