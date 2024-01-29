package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.Course;
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
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.xml.crypto.Data;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class QuestionBankController implements Initializable {
    public User previousUser;
    public Exam exam;
    public CoursePlan coursePlan;

    @FXML
    private ComboBox comboBox;
    @FXML
    private VBox mainVBox;
    @FXML
    private Group addQuestion;
    @FXML
    private Button backButton;

    public void setBackButton() {
        if(exam!=null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ExamPage.fxml"));
            try {
                Scene scene = new Scene(loader.load());
                ExamPageController examPageController = loader.getController();
                examPageController.exam = this.exam;
                examPageController.coursePlan = this.coursePlan;
                examPageController.user = this.previousUser;
                examPageController.setExamPage();
                HelloApplication.mainStage.setScene(scene);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
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

    }

    public void setAddQuestionButton() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddQuestion.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            AddQuestionController addQuestionController = loader.getController();
            addQuestionController.course = getComboBoxChoice();
            addQuestionController.previousUser = this.previousUser;
            HelloApplication.mainStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCards(ArrayList<Question> questions) {
        try {
            HBox eachQuestionBox;
            for (Question question : questions) {
                if(question instanceof SingleAnswer){
                    //short answer card controller initialize and setup
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("ShortAnswerQuestionCard.fxml"));
                    eachQuestionBox = loader.load();
                    CardController cardController = loader.getController();
                    cardController.question = question;
                    cardController.user = this.previousUser;
                    cardController.course = getComboBoxChoice();
                    cardController.questionBankController = this;
                    if(this.exam!=null){
                        cardController.exam = this.exam;
                    }
                    try {
                        cardController.setShortAnswerQuestionCardWithAnswer((SingleAnswer) question);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mainVBox.getChildren().add(eachQuestionBox);
                }
                else if(question instanceof LongAnswer){
                    //long answer card controller initialize and setup
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("LongAnswerQuestionCard.fxml"));
                    eachQuestionBox = loader.load();
                    CardController cardController = loader.getController();
                    cardController.user = this.previousUser;
                    cardController.question = question;
                    cardController.course = getComboBoxChoice();
                    cardController.questionBankController = this;
                    if(this.exam!=null){
                        cardController.exam = this.exam;
                    }
                    try {
                        cardController.setLongAnswerQuestionCardWithAnswer((LongAnswer) question);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mainVBox.getChildren().add(eachQuestionBox);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setComboBox() {
        mainVBox.getChildren().clear();
        Course selectedCourse = getComboBoxChoice();
        ArrayList<Question> questions = DataBase.getQuestions().get(selectedCourse);
        if(exam!=null){
            addQuestion.setVisible(false);
        }
        setCards(questions);
    }
    public Course getComboBoxChoice(){
        String courseName = comboBox.getSelectionModel().getSelectedItem().toString();
        Course course = DataBase.getCourses().get(courseName);
        return course;
    }
    public void setQuestionBankPage(){
        if(exam!=null){
            addQuestion.setVisible(false);
            backButton.setText("Submit");
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
