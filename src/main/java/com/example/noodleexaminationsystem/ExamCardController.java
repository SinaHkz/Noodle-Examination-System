package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.CoursePlan;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
public class ExamCardController implements Initializable {


    @FXML
    private Label examName;

    @FXML
    private Pane card;

    @FXML
    public void setExamCard(CoursePlan coursePlan) throws FileNotFoundException {
        card.setStyle("""
                -fx-background-color: transparet;
                -fx-border-radius: 20px;
                """);
        examName.setText(coursePlan.getName());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
