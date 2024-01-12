package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.CoursePlan;
import com.example.noodleexaminationsystem.Course.Exam;
import com.example.noodleexaminationsystem.User.User;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;

import java.net.URL;
import java.util.ResourceBundle;

public class ResultController implements Initializable {
    public User previousUser;
    public Exam exam;
    public CoursePlan coursePlan;

    public void setBackButton(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CreateExam.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            // Now that the FXML is loaded, get the controller and set the data
            CreateExamController createExamController = loader.getController();
            createExamController.coursePlan = this.coursePlan;
            System.out.println(previousUser);
            createExamController.user = this.previousUser;
            HelloApplication.mainStage.setScene(scene);
        } catch (Exception e) {
            System.out.println(e);
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
