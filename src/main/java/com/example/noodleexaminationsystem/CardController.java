package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.CoursePlan;
import com.example.noodleexaminationsystem.User.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

//    @FXML
//    private Label course;

    @FXML
    private Button courseName;

    @FXML
    private ImageView image;

//    @FXML
//    private Label teacherName;

    @FXML
    private Pane card;

    @FXML
    public void setCard(CoursePlan coursePlan) throws FileNotFoundException {
        card.setStyle("""
                -fx-background-color: transparet;
                -fx-border-radius: 20px;
                """);
        courseName.setText(coursePlan.getName());
        image.setImage(new Image(new FileInputStream(coursePlan.getPicturePath())));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
