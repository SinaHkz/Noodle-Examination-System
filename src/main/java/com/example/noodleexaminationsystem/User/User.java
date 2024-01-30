package com.example.noodleexaminationsystem.User;
import com.example.noodleexaminationsystem.Direct.Direct;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.example.noodleexaminationsystem.Course.CoursePlan;
import com.example.noodleexaminationsystem.DataBase;

import java.time.LocalDate;
import java.util.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonDeserialize
public class User {
    private String username;
    private String password;
    private String name;
    private Gender gender;
    private String lastName;
    private String email;
    private String picturePath = "/Users/sina/Desktop/University/Term_3/OOP/Noodle-Examination-System/src/main/resources/images/default-user-image.png";
    private LocalDate dob;
    private UserType userType;
    private ArrayList<Result> results = new ArrayList<>();
    @JsonManagedReference
    private Map<String ,CoursePlan> teacherCourses = new HashMap<>();
    private ArrayList<CoursePlan> StudentcoursePlans = new ArrayList<>();
    private ArrayList<Direct> directs = new ArrayList<>();

    public User(){

    }

    private User(String username, String password, String name, String lastName, String email, String picturePath, LocalDate dob, Gender gender, UserType userType) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.picturePath = picturePath;
        this.dob = dob;
        this.gender = gender;
        this.userType = userType;
    }
    //show the list of courses of a student


    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public ArrayList<CoursePlan> getStudentcoursePlans() {
        return StudentcoursePlans;
    }

    public void setStudentcoursePlans(ArrayList<CoursePlan> studentcoursePlans) {
        this.StudentcoursePlans = studentcoursePlans;
    }

    public User searchUser(String username) {
        for (User user : DataBase.getUsers().values()) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }

//    ----------------------------------------------------------------User static methods------------------------------------------------------------------------

    public static User signUp(String username, String password, String name, String lastName, String email, String picturePath, LocalDate dob, String gender,String userType) {
        String gender1 = gender.toUpperCase();
        String userType1 = userType.toUpperCase();
        if (DataBase.getUsers().containsKey(username))
            return null;
        User user1 = new User(username, password, name, lastName, email, picturePath, dob, Gender.valueOf(gender1),UserType.valueOf(userType1));
        DataBase.getUsers().put(username, user1);
        DataBase.usernameTrie.insert(username);
        return user1;
    }

    public static User login(String username, String password) {
        User user = DataBase.getUsers().get(username);
        if(user.getPassword().equals(password))
            return user;
        return null;
    }

    public static boolean deleteUser(User user){
        DataBase.getUsers().remove(user.getUsername(),user);
        return true;
    }

    //move it to course plan.------------------------------------------------------------
    public void deleteCoursePlan(CoursePlan coursePlan) {
        teacherCourses.remove(coursePlan);
    }
    public void archiveCoursePlan(CoursePlan coursePlan){
        coursePlan.setEnd(LocalDate.now());
    }



    public int resetPassword(String password, String newpassword) {
        if (this.password.equals(password)) {
            setPassword(newpassword);
            return 1;
        } else
            return 0;
    }

    public ArrayList<CoursePlan> getArchivedCoursePlans(LocalDate date) {
        ArrayList<CoursePlan> archivedCoursePlans = new ArrayList<>();
        ArrayList<CoursePlan> myCourses = this.getStudentcoursePlans();
        Collection<CoursePlan> teacherCourses = this.getTeacherCourses().values();
        for (CoursePlan coursePlan : myCourses) {
            if (!coursePlan.isActive())
                archivedCoursePlans.add(coursePlan);
        }
        for (CoursePlan coursePlan : teacherCourses) {
            if (!coursePlan.isActive())
                archivedCoursePlans.add(coursePlan);
        }
        return archivedCoursePlans;
    }

    public Direct getDirectByUser(User user){
        for (Direct direct: this.directs)
            if (direct.getUser1() == this && direct.getUser2() == user || direct.getUser2() == this && direct.getUser1() == user)
                return direct;
        return null;
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

    public Map<String, CoursePlan> getTeacherCourses() {
        return teacherCourses;
    }

    public void setTeacherCourses( Map<String, CoursePlan> teacherCourses) {
        this.teacherCourses = teacherCourses;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public ArrayList<Direct> getDirects() {
        return directs;
    }
}
