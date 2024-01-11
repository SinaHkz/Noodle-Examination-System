package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.CoursePlan;
import com.example.noodleexaminationsystem.Course.Exam;
import com.example.noodleexaminationsystem.Question.MultipleChoice;
import com.example.noodleexaminationsystem.Question.Question;
import com.example.noodleexaminationsystem.User.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ExamPageController implements Initializable {
    User user;
    Exam exam;
    CoursePlan coursePlan;

    @FXML
    Label examTitleLabel;
    @FXML
    Button startDateButton;
    @FXML
    Button endDateButton;
    @FXML
    Button examButton;
    @FXML
    Button addQuestionButton;
    @FXML
    Button checkResultsButton;
    @FXML
    Group finishExamNowButton;
    @FXML
    Group deleteExamButton;
    @FXML
    Label noQuestionLabel;
    @FXML
    Group submitAndExit;
    @FXML
    VBox questionsVbox;

    private void setCards(ArrayList<Question> questions, VBox cardVbox) {
        try {
            HBox eachQuestionBox;
            for (Question question : questions) {;
                if(question instanceof MultipleChoice){
                    //short answer card controller initialize and setup
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("ShortAnswerQuestionCard.fxml"));
                    eachQuestionBox = loader.load();
                    CardController cardController = loader.getController();
                    cardController.exam = exam;
                    cardController.coursePlan = this.coursePlan;
                    try {
                        cardController.setExamCard(exam);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                 //   eachQuestionBox.getChildren().add(cardBox);

                }
                else{
                    //long answer card controller initialize and setup
                }
//
//                FXMLLoader loader = new FXMLLoader();
//                loader.setLocation(getClass().getResource("ExamCard.fxml"));
//                Pane cardBox = loader.load();
//                CardController cardController = loader.getController();
//                cardController.exam = exam;
//                cardController.coursePlan = this.coursePlan;
//                try {
//                    cardController.setExamCard(exam);
//                } catch (Exception e) {
//                    System.out.println(e);
//                }
//                eachRowBox.getChildren().add(cardBox);
//                counter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void setExamPage(){

        if(coursePlan.getTeacher()!=user){
            addQuestionButton.setVisible(false);
            checkResultsButton.setVisible(false);
            finishExamNowButton.setVisible(false);
            deleteExamButton.setVisible(false);
        }
        else{
            submitAndExit.setVisible(false);
        }
        examTitleLabel.setText(this.exam.getExamTitle());
        startDateButton.setText(this.exam.getTimeStart().toString());
        endDateButton.setText(this.exam.getTimeEnd().toString());
        //setting question cards here
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
