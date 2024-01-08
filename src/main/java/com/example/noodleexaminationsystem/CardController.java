package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.CoursePlan;
import com.example.noodleexaminationsystem.Course.Exam;
import com.example.noodleexaminationsystem.User.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class CardController implements Initializable {
    public CoursePlan coursePlan;
    public User user;
    Exam exam;
    @FXML
    private Button name;

    @FXML
    private ImageView image;

    @FXML
    private Pane card;


    public void setCourseCard(CoursePlan coursePlan) throws FileNotFoundException {
        card.setStyle("""
                -fx-background-color: transparet;
                -fx-border-radius: 20px;
                """);
        name.setText(coursePlan.getName());
        image.setImage(new Image(new FileInputStream(coursePlan.getPicturePath())));

    }

    public void setCoursePlanButton() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("CoursePage.fxml"));
            Scene scene = new Scene(loader.load());

            // Now that the FXML is loaded, get the controller and set the data
            CoursePageController coursePageController = loader.getController();
            coursePageController.setCoursePlanPage(this.coursePlan);
            coursePageController.coursePlan = this.coursePlan;
            coursePageController.user = this.user;

            HelloApplication.mainStage.setScene(scene);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void setExamCard(Exam exam) {
//        card.setStyle("""
//                -fx-background-color: white;
//                -fx-opacity: 50;
//                -fx-border-radius: 20px;
//                """);
        name.setText(exam.getExamTitle());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Controller initialized. Name button: " + name);


    }

}
