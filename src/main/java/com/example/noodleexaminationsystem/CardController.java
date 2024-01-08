package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.Course.CoursePlan;
import com.example.noodleexaminationsystem.User.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CardController implements Initializable {
    public CoursePlan coursePlan;

    @FXML
    private Button courseName;

    @FXML
    private ImageView image;

//    @FXML
//    private Label teacherName;

    @FXML
    private Pane card;


    public void setCard(CoursePlan coursePlan) throws FileNotFoundException {
        card.setStyle("""
                -fx-background-color: transparet;
                -fx-border-radius: 20px;
                """);
        courseName.setText(coursePlan.getName());
        image.setImage(new Image(new FileInputStream(coursePlan.getPicturePath())));

    }
    public void setCoursePlanButton() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("CoursePage.fxml"));
            Scene scene = new Scene(loader.load());

            // Now that the FXML is loaded, get the controller and set the data
            CoursePlanController coursePlanController = loader.getController();
            coursePlanController.setCoursePlanPage(this.coursePlan);

            HelloApplication.mainStage.setScene(scene);
        } catch (Exception e) {
            System.out.println(e);
        }


//        HelloApplication.setScene("CoursePage.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
