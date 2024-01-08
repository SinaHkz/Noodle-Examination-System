package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.Course.CoursePlan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateCoursePlanController implements Initializable {
    @FXML
    TextField courseName;
    @FXML
    TextField coursePictureAddress;
    @FXML
    ComboBox comboBox;
    @FXML
    Label courseTaken;
    public void setBckButton() {
        HelloApplication.setScene("homePage.fxml");
    }
    public void setCreateCoursePlanButton(){
        boolean flag=false;
        comboBox.setStyle("border-colored-bottom:#096dde");
        if(courseName.getText().isEmpty()){
            courseName.setStyle("-fx-prompt-text-fill: red");
            flag=true;
        }
        if(coursePictureAddress.getText().isEmpty()){
            coursePictureAddress.setStyle("-fx-prompt-text-fill: red");
            flag=true;
        }
        if(comboBox.getSelectionModel().getSelectedItem() == null){
            comboBox.setStyle("-fx-border-color: red");
            flag = true;
        }
        if(flag) return;
        //error
//        CoursePlan coursePlan=CoursePlan.addCoursePlan(courseName.getText(),comboBox.getSelectionModel().getSelectedItem().toString(),HelloApplication.mainUser,coursePictureAddress.getText());
//        if (coursePlan == null ){
//            courseTaken.setVisible(true);
//        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list = FXCollections.observableArrayList();
        for (Course course : DataBase.getCourses().values() )
            list.add(course.getCourse());
        comboBox.setItems(list);
    }
}
