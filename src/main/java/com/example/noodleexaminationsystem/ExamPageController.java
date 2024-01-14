package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.CoursePlan;
import com.example.noodleexaminationsystem.Course.Exam;
import com.example.noodleexaminationsystem.Question.*;
import com.example.noodleexaminationsystem.User.Result;
import com.example.noodleexaminationsystem.User.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

public class ExamPageController implements Initializable {
    User user;
    Exam exam;
    CoursePlan coursePlan;
    ArrayList<CardController> questionCards = new ArrayList<>();

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

    private void setCards(ArrayList<Question> questions, VBox cardVbox,Boolean showWithAnswer) {
        try {
            HBox eachQuestionBox;
            for (Question question : questions) {;
                if(question instanceof SingleAnswer){
                    //short answer card controller initialize and setup
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("ShortAnswerQuestionCard.fxml"));
                    eachQuestionBox = loader.load();
                    CardController cardController = loader.getController();
                    cardController.question = question;
                    cardController.exam =this.exam;
                    cardController.examPageController = this;
                    try {
                        if(showWithAnswer)
                            cardController.setShortAnswerQuestionCardWithAnswer((SingleAnswer) question);
                        else
                            cardController.setShortAnswerQuestionCardWithoutAnswer((SingleAnswer) question);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    cardVbox.getChildren().add(eachQuestionBox);
                }
                else if(question instanceof LongAnswer){
                    //long answer card controller initialize and setup
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("LongAnswerQuestionCard.fxml"));
                    eachQuestionBox = loader.load();
                    CardController cardController = loader.getController();
                    cardController.question = question;
                    cardController.exam = this.exam;
                    cardController.examPageController = this;
                    try {
                        if(showWithAnswer)
                            cardController.setLongAnswerQuestionCardWithAnswer((LongAnswer) question);
                        else
                            cardController.setLongAnswerQuestionCardWithoutAnswer((LongAnswer) question);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    cardVbox.getChildren().add(eachQuestionBox);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //this method will get question cards from the login page controller instance
    public void setPreviousQuestionCards(){
        VBox tempVBox = HelloApplication.getUserPreviousAnswersHashMap().get(this.exam);
        this.questionsVbox.getChildren().setAll(tempVBox.getChildren());
    }



    public void setExamPage(){
        examTitleLabel.setText(this.exam.getExamTitle());
        startDateButton.setText(this.exam.getTimeStart().toString());
        endDateButton.setText(this.exam.getTimeEnd().toString());
            //the user is the teacher
            if(coursePlan.getTeacher()==this.user){
                if(!exam.hasStarted()){
                    addQuestionButton.setVisible(true);
                }
                else if(exam.hasEnded()){
                    checkResultsButton.setVisible(true);

                } else if (exam.isActive()) {
                    finishExamNowButton.setVisible(true);
                }
                //show questions with their answers
                setCards(exam.getQuestions(),questionsVbox,true);
            }
            //the user is a student
            else{
                deleteExamButton.setVisible(false);
                if(exam.isActive()){
                    submitAndExit.setVisible(true);
                    if(HelloApplication.getUserPreviousAnswersHashMap().get(this.exam)!=null){
                        setPreviousQuestionCards();
                    }
                    else{
                        setCards(exam.getQuestions(),questionsVbox,false);
                    }

                }
                else if(exam.hasEnded()){
                    setCards(exam.getQuestions(),questionsVbox,true);
                    //should also show the answers of that person
                }
        }



    }
    public void setDeleteExamButton(){
        coursePlan.getExams().remove(this.exam);
        //call back button when back button is created
        setBackButton();
    }
    public void setSubmitAndExitButton(){
        //it fucking works :)
        if(!exam.hasEnded()){
            Result result = Result.addResult(this.user,this.exam);
            for (CardController questionCard:this.questionCards) {
                Object answer = null;
                if(questionCard.question instanceof SingleAnswer){
                    if((answer = questionCard.getChoiceComboBox().getSelectionModel().getSelectedItem())!=null)
                        answer = answer.toString();
                    else{
                        answer = "";
                    }
                }
                else if(questionCard.question instanceof LongAnswer){
                    String answerString = questionCard.getLongAnswerQuestionTextField().getText();
                    answer = new LongAnswerStudentAnswer(answerString,0);
                }
                result.getAnswers().put(questionCard.question,answer);
            }
        }


        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //going back to course page
        setBackButton();

    }

    public void setCheckResultsButton(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GetResult.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            ResultController resultController = loader.getController();
            resultController.previousUser = this.user;
            System.out.println(exam);
            System.out.println(this.user);
            System.out.println(this.coursePlan);
            resultController.exam = this.exam;
            resultController.coursePlan = this.coursePlan;
            resultController.setLists();
            HelloApplication.mainStage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setFinishExamNowButton(){
        exam.setTimeEnd(LocalDateTime.now());
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("CoursePage.fxml"));
            Scene scene = new Scene(loader.load());
            // Now that the FXML is loaded, get the controller and set the data
            CoursePageController coursePageController = loader.getController();
            coursePageController.user = this.user;
            coursePageController.setCoursePlanPage(this.coursePlan);
            HelloApplication.mainStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setBackButton(){
        //set the map of exam and question card answers in the login page here after checking basic functionality
        HelloApplication.getUserPreviousAnswersHashMap().put(this.exam,this.questionsVbox);
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("CoursePage.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            // Now that the FXML is loaded, get the controller and set the data
            CoursePageController coursePageController = loader.getController();
            coursePageController.user = this.user;
            coursePageController.coursePlan = this.coursePlan;
            coursePageController.setCoursePlanPage(this.coursePlan);
            HelloApplication.mainStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
