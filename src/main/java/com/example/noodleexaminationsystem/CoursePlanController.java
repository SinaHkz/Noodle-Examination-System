package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.CoursePlan;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class CoursePlanController implements Initializable {
    @FXML
    Label teacherName;
    @FXML
    Label courseName;
    public void setCoursePlanPage(CoursePlan coursePlan){
        System.out.println("Im being called");
        try{
            teacherName.setText(coursePlan.getTeacher().getName());
            courseName.setText(coursePlan.getName());
        }
        catch (Exception e){
            System.out.println(e);
        }
//        teacherName.setText(coursePlan.getTeacher().getName());
//        courseName.setText(coursePlan.getName());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
