package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.CoursePlan;
import com.example.noodleexaminationsystem.Course.Exam;
import com.example.noodleexaminationsystem.Question.LongAnswer;
import com.example.noodleexaminationsystem.Question.Question;
import com.example.noodleexaminationsystem.Question.SingleAnswer;
import com.example.noodleexaminationsystem.User.Result;
import com.example.noodleexaminationsystem.User.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;


public class ResultController implements Initializable {
    public User previousUser;
    public Exam exam;
    public CoursePlan coursePlan;

    @FXML
    private ListView listView;
    @FXML
    private VBox resultBox;
    @FXML
    private Button checkButton;
    @FXML
    private Button backToList;

    public void setShowResultButton() {
        String username = listView.getSelectionModel().getSelectedItem().toString();
        System.out.println(username);
        Result result = null;
        for (Result tempResult : exam.getResults())
            if (tempResult.getStudent().getUsername().compareTo(username) == 0) {
                result = tempResult;
                break;
            }
        for (Question question : result.getAnswers().keySet()) {
            if (question instanceof LongAnswer) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("LongAnswerQuestionCard.fxml"));
                    HBox eachQuestionBox = loader.load();
                    CardController cardController = loader.getController();
//                    cardController.exam = this.exam;
//                    cardController.coursePlan = this.coursePlan;
//                    cardController.user = this.previousUser;
                    cardController.setLongAnswerQuestionCardWithUserAnswer((LongAnswer) question, (String) result.getAnswers().get(question));
                    resultBox.getChildren().add(eachQuestionBox);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (question instanceof SingleAnswer) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ShortAnswerQuestionCard.fxml"));
                    HBox eachQuestionBox = loader.load();
                    CardController cardController = loader.getController();
                    cardController.setShortAnswerQuestionCardWithUserAnswer((SingleAnswer) question, (String) result.getAnswers().get(question));
                    resultBox.getChildren().add(eachQuestionBox);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        backToList.setVisible(true);
        checkButton.setVisible(false);
        resultBox.setVisible(true);
        listView.setVisible(false);

    }

    public void setBackToList(){
        resultBox.setVisible(false);
        resultBox.getChildren().clear();
        listView.setVisible(true);
        backToList.setVisible(false);
        checkButton.setVisible(true);
    }

    public void setLists() {
        ObservableList<String> users = FXCollections.observableArrayList();
        for (Result result : this.exam.getResults()) {
            users.add(result.getStudent().getUsername());
        }
        listView.setItems(users);
    }


    public void setBackButton() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ExamPage.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            // Now that the FXML is loaded, get the controller and set the data
            ExamPageController examPageController = loader.getController();
            examPageController.exam = this.exam;
            examPageController.coursePlan = this.coursePlan;
            examPageController.user = this.previousUser;
            examPageController.setExamPage();
            HelloApplication.mainStage.setScene(scene);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
