package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.User.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class QuestionBankController implements Initializable {
    public User previousUser;

    @FXML
    private ComboBox comboBox;


    public void setBackButton() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("homePage.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            HomePageController homePageController = loader.getController();
            homePageController.user = this.previousUser;
            homePageController.setHomePage(previousUser);
            HelloApplication.mainStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAddQuestionButton(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddQuestion.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            AddQuestionController addQuestionController = loader.getController();
            addQuestionController.previousUser = this.previousUser;
            HelloApplication.mainStage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> courses = FXCollections.observableArrayList();
        for (Course course : DataBase.courses.values())
            courses.add(course.getCourse());
        comboBox.setItems(courses);
    }
}
