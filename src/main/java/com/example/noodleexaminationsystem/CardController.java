package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.CoursePlan;
import com.example.noodleexaminationsystem.Course.Exam;
import com.example.noodleexaminationsystem.Question.LongAnswer;
import com.example.noodleexaminationsystem.Question.MultipleChoice;
import com.example.noodleexaminationsystem.Question.Question;
import com.example.noodleexaminationsystem.Question.SingleAnswer;
import com.example.noodleexaminationsystem.User.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CardController implements Initializable {
    public CoursePlan coursePlan;
    public User user;
    //public int test =0;
    Exam exam;
    @FXML
    private Button name;

    @FXML
    private ImageView image;

    @FXML
    private Pane card;
    @FXML
    private HBox questionHBox;
    @FXML
    private Label questionLabel;
    @FXML
    private TextField longAnswerQuestionTextField;
    @FXML
    private  Label shortAnswerChoiceLabel;
    @FXML
    private ComboBox choiceComboBox;



    public void setCourseCard(CoursePlan coursePlan) throws FileNotFoundException {
        card.setStyle("""
                -fx-background-color: transparet;
                -fx-border-radius: 20px;
                """);
        name.setText(coursePlan.getName());
        image.setImage(new Image(new FileInputStream(coursePlan.getPicturePath())));
        //this.test = 5;

    }

    public void setCoursePlanButton() {
        try {
            //testing to see if the  value gets initialized when setting the controller which failed
            //System.out.println(this.test);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("CoursePage.fxml"));
            Scene scene = new Scene(loader.load());

            // Now that the FXML is loaded, get the controller and set the data
            CoursePageController coursePageController = loader.getController();
            coursePageController.coursePlan = this.coursePlan;
            coursePageController.user = this.user;
            coursePageController.setCoursePlanPage(this.coursePlan);

            HelloApplication.mainStage.setScene(scene);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void setExamButton() {
        try {
            if((!this.exam.hasStarted()) && (this.user!=this.coursePlan.getTeacher())){
                //set a label to show that the exam is yet to be started
                // <<<<<<<<<<<<<<-------------------------------
                return;
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ExamPage.fxml"));
            Scene scene = new Scene(loader.load());

            // Now that the FXML is loaded, get the controller and set the data
            ExamPageController examPageController = loader.getController();
            //System.out.println(this.user);
            examPageController.user = this.user;
            examPageController.exam = this.exam;
            examPageController.coursePlan = this.coursePlan;
            examPageController.setExamPage();

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
    public void setLongAnswerQuestionCardWithoutAnswer(LongAnswer question){
        questionLabel.setText(question.getQuestion());
    }
    public void setShortAnswerQuestionCardWithoutAnswer(SingleAnswer question){
        questionLabel.setText(question.getQuestion());
        String answers = "";
        for (String answer: question.getChoices()) {
            answers += answer+ "\n";
        }
        shortAnswerChoiceLabel.setText(answers);
        ObservableList<Integer> choiceNumbers = FXCollections.observableArrayList();
        for(int i=1;i<=question.getCountOfChoice().getValue();i++){
            choiceNumbers.add(i);
        }
        choiceComboBox.setItems(choiceNumbers);
    }
    public void setLongAnswerQuestionCardWithAnswer(LongAnswer question){
        questionLabel.setText(question.getQuestion());
        longAnswerQuestionTextField.editableProperty().set(false);
        longAnswerQuestionTextField.setText(question.getAnswer());
    }
    public void setShortAnswerQuestionCardWithAnswer(SingleAnswer question){
        questionLabel.setText(question.getQuestion());
        String answers = "";
        for (String answer: question.getChoices()) {
            answers += answer+ "\n";
        }
        shortAnswerChoiceLabel.setText(answers);
        choiceComboBox.setVisible(false);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}
