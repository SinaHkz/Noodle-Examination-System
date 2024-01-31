package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.CoursePlan;
import com.example.noodleexaminationsystem.Course.Exam;
import com.example.noodleexaminationsystem.Question.LongAnswer;
import com.example.noodleexaminationsystem.Question.LongAnswerStudentAnswer;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.io.FileInputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;


public class ResultController implements Initializable {
    public User previousUser;
    public Exam exam;
    public CoursePlan coursePlan;
    public User student = null;
    double score =0;

    static HashMap<Question, TextField> cardControllers = new HashMap<>();

    @FXML
    private ListView listView;
    @FXML
    private Label scoreLabel;
    @FXML
    private VBox resultBox;
    @FXML
    private Button checkButton;
    @FXML
    private Button backToList;
    @FXML
    private Label usernameLabel;
    @FXML
    private ImageView profileImage;


    public void setShowResultButton() {
        String username = listView.getSelectionModel().getSelectedItem().toString();
        Result result = null;
        for (Result tempResult : exam.getResults())
            if (tempResult.getStudent().getUsername().compareTo(username) == 0) {
                student = tempResult.getStudent();
                result = tempResult;
                break;
            }

        usernameLabel.setText(username);
        try {
            FileInputStream stream = new FileInputStream(student.getPicturePath());
            Image newImage = new Image(stream);
            profileImage.setImage(newImage);
            final Circle clip = new Circle(123.5, 136, 110);
            profileImage.setClip(clip);
            profileImage.setImage(newImage);
        }catch (Exception e){
            e.printStackTrace();
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
                    cardController.setLongAnswerQuestionCardWithUserAnswer((LongAnswer) question, ((LongAnswerStudentAnswer) result.getAnswers().get(question)).getStudentAnswer());

//                    create new text field to get score
                    TextField newTextField = new TextField();
                    newTextField.setPromptText("Score");
                    score += ((LongAnswerStudentAnswer) result.getAnswers().get(question)).getScore();
                    newTextField.setText(Double.toString(((LongAnswerStudentAnswer) result.getAnswers().get(question)).getScore()));
                    cardControllers.put(question, newTextField);
                    eachQuestionBox.getChildren().add(newTextField);

                    resultBox.getChildren().add(eachQuestionBox);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (question instanceof SingleAnswer) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ShortAnswerQuestionCard.fxml"));
                    HBox eachQuestionBox = loader.load();
                    CardController cardController = loader.getController();
                    TextField newTextField = new TextField();
                    if (checkMultipleChoiceAnswer(result, (SingleAnswer) question)){
                        score += 1;
                        newTextField.setText("True");}
                    else
                        newTextField.setText("False");
                    newTextField.setEditable(false);
                    eachQuestionBox.getChildren().add(newTextField);
                    cardController.setShortAnswerQuestionCardWithUserAnswer((SingleAnswer) question, Integer.toString((Integer) result.getAnswers().get(question)+1));

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
        scoreLabel.setVisible(true);
        scoreLabel.setText("score:"+ score);
    }

    public static boolean checkMultipleChoiceAnswer(Result result, SingleAnswer question) {
        if ((Integer) result.getAnswers().get(question) == question.getAnswerValue())
            return true;
        return false;
    }


    public void setBackToList() {
        usernameLabel.setText("");
        profileImage.setVisible(false);
        Result result = null;
        for (Result tempResult : exam.getResults())
            if (tempResult.getStudent() == student) {
                result = tempResult;
                break;
            }
        for (Question question : cardControllers.keySet()) {
            if (!cardControllers.get(question).getText().equals("")) {
                try {
                    Double score = Double.parseDouble(cardControllers.get(question).getText());
                    ((LongAnswerStudentAnswer) result.getAnswers().get(question)).setScore(score);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        resultBox.setVisible(false);
        resultBox.getChildren().clear();
        listView.setVisible(true);
        backToList.setVisible(false);
        checkButton.setVisible(true);
    }

    public void setLists() {
        usernameLabel.setText("");
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
