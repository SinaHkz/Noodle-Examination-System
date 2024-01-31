package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.Course;
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
import javafx.scene.control.TextField;
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
    double score =0;

    @FXML
    Label examTitleLabel;
    @FXML
    Button startDateButton;
    @FXML
    Button endDateButton;
    @FXML
    Label scoreLabel;
    @FXML
    Button examButton;
    @FXML
    Button addQuestionButton;
    @FXML
    Button backButton;
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
    @FXML
    Button questionBank;

    private void setCards(ArrayList<Question> questions, VBox cardVbox, boolean showWithAnswer) {
        try {
            HBox eachQuestionBox;
            for (Question question : questions) {
                ;
                if (question instanceof SingleAnswer) {
                    //short answer card controller initialize and setup
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("ShortAnswerQuestionCard.fxml"));
                    eachQuestionBox = loader.load();
                    CardController cardController = loader.getController();
                    cardController.question = question;
                    cardController.user = this.user;
                    cardController.exam = this.exam;
                    cardController.coursePlan = this.coursePlan;
                    cardController.examPageController = this;
                    cardController.examPageVbox = cardVbox;
                    try {
                        if (showWithAnswer) {
                            Result studentResult = null;
                            for (Result result : this.exam.getResults()) {
                                if (result.getStudent() == this.user)
                                    studentResult = result;
                            }
                            //shows users answers
                            if (studentResult != null) {
                                TextField textField = new TextField();
                                if (ResultController.checkMultipleChoiceAnswer(studentResult, (SingleAnswer) question)) {
                                    textField.setText("True");
                                    score += 1;
                                    eachQuestionBox.setStyle("-fx-background-color: rgba(62,224,62,0.3)");
                                } else {
                                    textField.setText("False");
                                    eachQuestionBox.setStyle("-fx-background-color: rgba(210,33,33,0.3)");
                                }
                                textField.setEditable(false);
                                eachQuestionBox.getChildren().add(textField);
                                cardController.setShortAnswerQuestionCardWithUserAnswer((SingleAnswer) question, Integer.toString((Integer) studentResult.getAnswers().get(question) + 1));
                            }
                            //if user does not have a result shows previous result
                            else {
                                cardController.setShortAnswerQuestionCardWithAnswer((SingleAnswer) question);
                            }

                        } else
                            cardController.setShortAnswerQuestionCardWithoutAnswer((SingleAnswer) question);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    cardVbox.getChildren().add(eachQuestionBox);
                } else if (question instanceof LongAnswer) {
                    //long answer card controller initialize and setup
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("LongAnswerQuestionCard.fxml"));
                    eachQuestionBox = loader.load();
                    CardController cardController = loader.getController();
                    cardController.question = question;
                    cardController.exam = this.exam;
                    cardController.user = this.user;
                    cardController.coursePlan = this.coursePlan;
                    cardController.examPageController = this;
                    cardController.examPageVbox = cardVbox;
                    try {
                        if (showWithAnswer) {
                            Result studentResult = null;
                            for (Result result : this.exam.getResults()) {
                                if (result.getStudent() == this.user)
                                    studentResult = result;
                            }
                            if (studentResult != null) {

                                TextField textField = new TextField();
                                textField.setPromptText("Score");
                                score += (((LongAnswerStudentAnswer) studentResult.getAnswers().get(question)).getScore());
                                textField.setText(Double.toString(((LongAnswerStudentAnswer) studentResult.getAnswers().get(question)).getScore()));
                                cardController.setLongAnswerQuestionCardWithUserAnswer((LongAnswer) question, ((LongAnswerStudentAnswer) studentResult.getAnswers().get(question)).getStudentAnswer());
                                eachQuestionBox.getChildren().add(textField);
                            }
                            //if user does not have a result shows a
                            else {
                                cardController.setLongAnswerQuestionCardWithAnswer((LongAnswer) question);
                            }
                        } else {
                            cardController.setLongAnswerQuestionCardWithoutAnswer((LongAnswer) question);
                        }
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
    public void setPreviousQuestionCards() {
        VBox tempVBox = HelloApplication.getUserPreviousAnswersHashMap().get(this.user).get(this.exam);
        this.questionsVbox.getChildren().setAll(tempVBox.getChildren());
    }


    public void setExamPage() {
        examTitleLabel.setText(this.exam.getExamTitle());
        startDateButton.setText(this.exam.getTimeStart().toString());
        endDateButton.setText(this.exam.getTimeEnd().toString());
        //the user is the teacher
        if (coursePlan.getTeacher() == this.user) {
            if (!exam.hasStarted()) {
                addQuestionButton.setVisible(true);
                questionBank.setVisible(true);
            } else if (exam.hasEnded()) {
                checkResultsButton.setVisible(true);

            } else if (exam.isActive()) {
                finishExamNowButton.setVisible(true);
            }
            //show questions with their answers
            setCards(exam.getQuestions(), questionsVbox, true);
        }
        //the user is a student
        else {
            deleteExamButton.setVisible(false);
            if (exam.isActive()) {
                submitAndExit.setVisible(true);
                //checking to see if this user has result for this exam
                if (HelloApplication.getUserPreviousAnswersHashMap().get(this.user) != null) {
                    if (HelloApplication.getUserPreviousAnswersHashMap().get(this.user).get(this.exam) != null)
                        setPreviousQuestionCards();
                    else
                        setCards(exam.getQuestions(), questionsVbox, false);

                } else {
                    setCards(exam.getQuestions(), questionsVbox, false);
                }

            } else if (exam.hasEnded()) {
                setCards(exam.getQuestions(), questionsVbox, true);
                //should also show the answers of that person
                scoreLabel.setVisible(true);
                scoreLabel.setText("score: "+ score);
            }
        }


    }

    public void setDeleteExamButton() {
        coursePlan.getExams().remove(this.exam);
        //call back button when back button is created
        setBackButton();
    }

    public void setSubmitAndExitButton() {
        //it fucking works :)
        if (!exam.hasEnded()) {
            Result result = Result.addResult(this.user, this.exam);
            for (CardController questionCard : this.questionCards) {
                Object answer = -1;
                if (questionCard.question instanceof SingleAnswer) {
                    if (questionCard.getChoiceComboBox().getSelectionModel().getSelectedItem() != null) {
                        answer = questionCard.getChoiceComboBox().getSelectionModel().getSelectedItem();
                        if (!answer.equals("Delete answer")) {
                            answer = Integer.parseInt((String) answer) - 1;
                        }

                    }

                } else if (questionCard.question instanceof LongAnswer) {
                    String answerString = questionCard.getLongAnswerQuestionTextField().getText();
                    answer = new LongAnswerStudentAnswer(answerString, 0);
                }
                result.getAnswers().put(questionCard.question, answer);
            }
        }
        //going back to course page
        setBackButton();

    }

    public void setCheckResultsButton() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GetResult.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            ResultController resultController = loader.getController();
            resultController.previousUser = this.user;
            resultController.exam = this.exam;
            resultController.coursePlan = this.coursePlan;
            resultController.setLists();
            HelloApplication.mainStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setFinishExamNowButton() {
        exam.setTimeEnd(LocalDateTime.now());
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("CoursePage.fxml"));
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

    public void setBackButton() {
        FXMLLoader loader = new FXMLLoader();
        //set the map of exam and question card answers in the login page here after checking basic functionality
        if(this.user!=this.coursePlan.getTeacher()){
            if(HelloApplication.getUserPreviousAnswersHashMap().get(this.user)==null)
                HelloApplication.getUserPreviousAnswersHashMap().put(this.user,new HashMap<>());
            HelloApplication.getUserPreviousAnswersHashMap().get(this.user).put(this.exam, this.questionsVbox);
        }

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

    public void setAddQuestionButton() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("AddQuestion.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            // Now that the FXML is loaded, get the controller and set the data
            AddQuestionController addQuestionController = loader.getController();
            addQuestionController.previousUser = this.user;
            addQuestionController.exam = this.exam;
            addQuestionController.coursePlan = this.coursePlan;
            HelloApplication.mainStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setQuestionBank() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("QuestionsBank.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            // Now that the FXML is loaded, get the controller and set the data
            QuestionBankController questionBankController = loader.getController();
            questionBankController.previousUser = this.user;
            questionBankController.exam = this.exam;
            questionBankController.coursePlan = this.coursePlan;
            questionBankController.setQuestionBankPage();
            HelloApplication.mainStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
