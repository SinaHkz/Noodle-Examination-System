package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.Course.CoursePlan;
import com.example.noodleexaminationsystem.Course.Exam;
import com.example.noodleexaminationsystem.User.Gender;
import com.example.noodleexaminationsystem.User.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    @FXML
    private VBox cardVBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<CoursePlan> coursePlans = new ArrayList<>();
        LocalDate date = LocalDate.parse("2000-02-01");
        CoursePlan coursePlan = new CoursePlan(new Course("OOP"), "Object Oriented", User.signUp("a", "a", "a", "a", "a", "a", date, "FEMALE"), new Exam("a"), new Date(), new Date(), "a");
        CoursePlan coursePlan1 = new CoursePlan(new Course("OOP"), "Object Oriented", User.signUp("a", "a", "a", "a", "a", "a", date, "FEMALE"), new Exam("a"), new Date(), new Date(), "a");
        coursePlans.add(coursePlan);
        try {
            HBox cardBox = new HBox();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("coursePlanCard.fxml"));
            HBox temp = loader.load();
            CardController cardController = loader.getController();
            cardController.setCard(coursePlan);
            cardVBox.getChildren().add(temp);



            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(getClass().getResource("coursePlanCard.fxml"));
            HBox temp1 = loader1.load();
            CardController cardController1 = loader1.getController();
            cardController1.setCard(coursePlan);
            cardVBox.getChildren().add(temp1);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
