package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.Course;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateCourseController implements Initializable {

    @FXML
    BorderPane signupPane;
    @FXML
    TextField courseTitle;
    @FXML
    Label courseTaken;

    public void setBackToHomePageButton(){
        HelloApplication.setScene("homePage.fxml");
    }


    public void setCreateCourseButton() {
        boolean flag = false;
        if(courseTitle.getText().isEmpty()){
            courseTitle.setStyle("-fx-prompt-text-fill: red");
            flag= true;
        }
        if (flag) return;

        Course course = Course.addCourse(courseTitle.getText());
        if(course == null){
            courseTaken.setVisible(true);
            return;
        }

        HelloApplication.setScene("homePage.fxml");

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
