package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.Course.CoursePlan;
import com.example.noodleexaminationsystem.Course.Exam;
import com.example.noodleexaminationsystem.Question.LongAnswer;
import com.example.noodleexaminationsystem.Question.SingleAnswer;
import com.example.noodleexaminationsystem.User.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddQuestionController implements Initializable {
    public User previousUser;
    public Exam exam;
    public CoursePlan coursePlan;
    public Course course;

    @FXML
    VBox LongAnswerVbox;
    @FXML
    VBox SingleVbox;
    @FXML
    Button singleAnswerButton;
    @FXML
    Button longAnswerButton;
    @FXML
    ToggleGroup toggleGroup = new ToggleGroup();
    @FXML
    TextArea singleAnswerQuestion;
//    @FXML
//    VBox MultipleAnswerVbox;
    @FXML
    TextArea LongAnswerText;
    @FXML
    Label emptyQuestionLabel;
    @FXML
    Label emptyLabelAnswer;
    @FXML
    TextArea LongAnswerQuestion;
    //options
    @FXML
    RadioButton option1;
    @FXML
    RadioButton option2;

    @FXML
    RadioButton option3;

    @FXML
    RadioButton option4;
    @FXML
    RadioButton option5;

    @FXML
    RadioButton option6;

    @FXML
    RadioButton option7;

    @FXML
    RadioButton option8;


    @FXML
    TextArea TextArea11;
    @FXML
    TextArea TextArea22;
    @FXML
    TextArea TextArea33;
    @FXML
    TextArea TextArea44;
    @FXML
    TextArea TextArea55;
    @FXML
    TextArea TextArea66;
    @FXML
    TextArea TextArea77;
    @FXML
    TextArea TextArea88;



    public void setBackButton() {
        //the person has come to this page from exam page and wanted to add questions to exam
        if(exam!=null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ExamPage.fxml"));
            try {
                Scene scene = new Scene(loader.load());
                ExamPageController examPageController = loader.getController();
                examPageController.user = this.previousUser;
                examPageController.coursePlan = this.coursePlan;
                examPageController.exam = this.exam;
                examPageController.setExamPage();
                HelloApplication.mainStage.setScene(scene);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //later on when question bank is complete check this part of the function
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("QuestionsBank.fxml"));
            try {
                Scene scene = new Scene(loader.load());
                QuestionBankController questionBankController = loader.getController();
                questionBankController.previousUser = this.previousUser;
                HelloApplication.mainStage.setScene(scene);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public void setSubmitButton() {
        ArrayList<String> choices = new ArrayList<>();
        int answerValue = -1;
        if (SingleVbox.isVisible()) {
            if(!TextArea11.getText().trim().isEmpty()){
                choices.add(TextArea11.getText());
                if(option1.isSelected())
                    answerValue = 0;
            }
            if(!TextArea22.getText().trim().isEmpty()){
                choices.add(TextArea22.getText());
                if(option2.isSelected())
                    answerValue = 1;
            }
            if(!TextArea33.getText().trim().isEmpty()){
                choices.add(TextArea33.getText());
                if(option3.isSelected())
                    answerValue = 2;
            }
            if(!TextArea44.getText().trim().isEmpty()){
                choices.add(TextArea44.getText());
                if(option4.isSelected())
                    answerValue = 3;
            }
            if(!TextArea55.getText().trim().isEmpty()){
                choices.add(TextArea55.getText());
                if(option5.isSelected())
                    answerValue = 4;
            }
            if(!TextArea66.getText().trim().isEmpty()){
                choices.add(TextArea66.getText());
                if(option6.isSelected())
                    answerValue = 5;
            }
            if(!TextArea77.getText().trim().isEmpty()){
                choices.add(TextArea77.getText());
                if(option7.isSelected())
                    answerValue = 6;
            }
            if(!TextArea88.getText().trim().isEmpty()){
                choices.add(TextArea88.getText());
                if(option8.isSelected())
                    answerValue = 7;
            }
            //now if the answer value is selected on an empty text field pop up will be shown
            System.out.println(answerValue);
            if(answerValue==-1){
                //pop up
                //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            }
            else{
                answerValue = answerValue-(answerValue-(choices.size()-1));
                if(this.exam!=null)
                    this.exam.addSingleAnswerQuestionToExam(singleAnswerQuestion.getText(),this.previousUser,this.coursePlan.getCourse(),answerValue,choices);
                else {
                    if(course!=null){
                        SingleAnswer.createQuestion(singleAnswerQuestion.getText(),this.previousUser,this.course,answerValue,choices);
                    }
                    else
                        SingleAnswer.createQuestion(singleAnswerQuestion.getText(),this.previousUser,this.coursePlan.getCourse(),answerValue,choices);
                }
                //goes to previous page
                setBackButton();
            }


        } else if (LongAnswerVbox.isVisible()) {
           if(LongAnswerQuestion.getText().trim().isEmpty()) {
               //make question label on
               LongAnswerQuestion.setStyle("-fx-prompt-text-fill: red");
               return;
           }
           if(LongAnswerText.getText().trim().isEmpty()){
               //make question label on
               LongAnswerText.setStyle("-fx-prompt-text-fill: red");
               return;
           }
           if(exam!=null){
               this.exam.addLongAnswerQuestionToExam(LongAnswerQuestion.getText(),this.previousUser,this.coursePlan.getCourse(),LongAnswerText.getText());
           }
           else{
               if(this.course!=null){
                   System.out.println(course.getCourse());
                   LongAnswer.createQuestion(LongAnswerQuestion.getText(),this.previousUser,this.course,LongAnswerText.getText());
               }
               else
                   LongAnswer.createQuestion(LongAnswerQuestion.getText(),this.previousUser,this.coursePlan.getCourse(),LongAnswerText.getText());
           }
            //goes to previous page
            setBackButton();
        }
    }
    public void setLongAnswer(){
        if (!LongAnswerVbox.isVisible()){
            LongAnswerVbox.setVisible(true);
            SingleVbox.setVisible(false);
            singleAnswerButton.getStyleClass().removeAll("selected-buttons");
            longAnswerButton.getStyleClass().addAll("selected-buttons");
        }
    }
    public void setSingleAnswer(){
        if (!SingleVbox.isVisible()){
            SingleVbox.setVisible(true);
            LongAnswerVbox.setVisible(false);
            longAnswerButton.getStyleClass().removeAll("selected-buttons");
            singleAnswerButton.getStyleClass().addAll("selected-buttons");
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        option1.setToggleGroup(toggleGroup);
        option2.setToggleGroup(toggleGroup);
        option3.setToggleGroup(toggleGroup);
        option4.setToggleGroup(toggleGroup);
        option5.setToggleGroup(toggleGroup);
        option6.setToggleGroup(toggleGroup);
        option7.setToggleGroup(toggleGroup);
        option8.setToggleGroup(toggleGroup);


    }
}
