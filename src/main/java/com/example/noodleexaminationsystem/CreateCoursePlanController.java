package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.User.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;

public class CreateCoursePlanController implements Initializable {
    public User user;

    @FXML
    TextField courseName;
    @FXML
    TextField coursePictureAddress;
    @FXML
    ComboBox comboBox;

    public void setBckButton() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("homePage.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            HomePageController homePageController = loader.getController();
            homePageController.user = this.user;
            homePageController.setHomePage(user);
            HelloApplication.mainStage.setScene(scene);

        } catch (Exception e) {
            e.printStackTrace();
        }

//       HelloApplication.setScene("homePage.fxml");
    }

    public void setCreateCoursePlanButton() {
        boolean flag = false;
        comboBox.setStyle("border-colored-bottom:#096dde");
        if (courseName.getText().isEmpty()) {
            courseName.setStyle("-fx-prompt-text-fill: red");
            flag = true;
        }
        if (coursePictureAddress.getText().isEmpty()) {
            coursePictureAddress.setStyle("-fx-prompt-text-fill: red");
            flag = true;
        }
        if (comboBox.getSelectionModel().getSelectedItem() == null) {
            comboBox.setStyle("-fx-border-color: red");
            flag = true;
        }
        if (flag) return;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list = FXCollections.observableArrayList();
        Collection<Course> courses = DataBase.getCourses().values();
        for (Course course : courses)
            list.add(course.getCourse());
        comboBox.setItems(list);
    }
}
