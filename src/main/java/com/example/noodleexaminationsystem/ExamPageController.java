package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.CoursePlan;
import com.example.noodleexaminationsystem.Course.Exam;
import com.example.noodleexaminationsystem.Question.LongAnswer;
import com.example.noodleexaminationsystem.Question.MultipleChoice;
import com.example.noodleexaminationsystem.Question.Question;
import com.example.noodleexaminationsystem.Question.SingleAnswer;
import com.example.noodleexaminationsystem.User.Result;
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
import java.util.*;

public class ExamPageController implements Initializable {
    User user;
    Exam exam;
    CoursePlan coursePlan;
    HashMap<Question,Object> answers = new HashMap<>();
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
            if(exam.isActive()){
                submitAndExit.setVisible(true);
                setCards(exam.getQuestions(),questionsVbox,false);
            }
            else if(exam.hasEnded()){
                setCards(exam.getQuestions(),questionsVbox,true);
            }

        }
    }
    public void setDeleteExamButton(){
        coursePlan.getExams().remove(this.exam);
        //call back button when back button is created
    }
    public void setSubmitAndExitButton(){
        //it fucking works :)
        for (CardController questionCard:this.questionCards) {
            System.out.println(questionCard.question.getQuestion());
        }
        Result result = Result.addResult(this.user,this.exam);
        for (Map.Entry<Question,Object> answer : this.answers.entrySet()) {
            result.getAnswers().put(answer.getKey(), answer.getValue());
        }
        //go back to course page
        //handel that if user has a result (meaning that they have already submitted the exam) it would not have an option
        //of submitting it again
        //feature: show the person their own answers after submitting the exam
        //or maybe better: the person wouldn't be able to enter the exam again
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
