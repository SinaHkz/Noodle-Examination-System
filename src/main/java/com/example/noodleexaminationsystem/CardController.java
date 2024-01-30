package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.Course.CoursePlan;
import com.example.noodleexaminationsystem.Course.Exam;
import com.example.noodleexaminationsystem.Question.*;
import com.example.noodleexaminationsystem.User.Result;
import com.example.noodleexaminationsystem.User.User;
import com.example.noodleexaminationsystem.User.UserType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class CardController implements Initializable {
    public CoursePlan coursePlan;
    public User user;
    public Question question;
    public Exam exam;
    public Course course;
    public VBox examPageVbox;
    public QuestionBankController questionBankController;
    public ExamPageController examPageController;
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
    private TextArea longAnswerQuestionTextField;
    @FXML
    private Label shortAnswerChoiceLabel;
    @FXML
    private ComboBox choiceComboBox;
    @FXML
    private VBox cardBox;
    @FXML
    private Button deleteQuestion;
    @FXML
    private Button minusButton;
    @FXML
    private Button plusButton;
    private List<String> hexcolors = List.of("rgb(255,255,255,0.5)", "rgb(47, 77, 178,0.3)" , "rgb(142,158,213,0.4)");

    //___________________________________________________ getter/setter________________________________________________________


    public CoursePlan getCoursePlan() {
        return coursePlan;
    }

    public void setCoursePlan(CoursePlan coursePlan) {
        this.coursePlan = coursePlan;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public ExamPageController getExamPageController() {
        return examPageController;
    }

    public void setExamPageController(ExamPageController examPageController) {
        this.examPageController = examPageController;
    }

    public Button getName() {
        return name;
    }

    public void setName(Button name) {
        this.name = name;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public Pane getCard() {
        return card;
    }

    public void setCard(Pane card) {
        this.card = card;
    }

    public HBox getQuestionHBox() {
        return questionHBox;
    }

    public void setQuestionHBox(HBox questionHBox) {
        this.questionHBox = questionHBox;
    }

    public Label getQuestionLabel() {
        return questionLabel;
    }

    public void setQuestionLabel(Label questionLabel) {
        this.questionLabel = questionLabel;
    }

    public TextArea getLongAnswerQuestionTextField() {
        return longAnswerQuestionTextField;
    }

    public void setLongAnswerQuestionTextField(TextArea longAnswerQuestionTextField) {
        this.longAnswerQuestionTextField = longAnswerQuestionTextField;
    }

    public Label getShortAnswerChoiceLabel() {
        return shortAnswerChoiceLabel;
    }

    public void setShortAnswerChoiceLabel(Label shortAnswerChoiceLabel) {
        this.shortAnswerChoiceLabel = shortAnswerChoiceLabel;
    }

    public ComboBox getChoiceComboBox() {
        return choiceComboBox;
    }

    public void setChoiceComboBox(ComboBox choiceComboBox) {
        this.choiceComboBox = choiceComboBox;
    }
    //______________________________________________________ methods ____________________________________________________

    public void setCourseCard(CoursePlan coursePlan) {
        card.setStyle("""
                -fx-background-color: transparet;
                -fx-border-radius: 20px;
                """);
        name.setText(coursePlan.getName());
        try {
            image.setImage(new Image(new FileInputStream(coursePlan.getPicturePath())));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            if ((!this.exam.hasStarted()) && (this.user != this.coursePlan.getTeacher())) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("popUp.fxml"));
                Scene scene = new Scene(loader.load());
                PopUpController popUpController = loader.getController();
                popUpController.setnotActive();
                Stage stage = new Stage();
                popUpController.stage = stage;
                stage.setScene(scene);
                stage.show();
                return;
            }
            //check if the exam is active and user already has a result for that exam not to allow enter
            if (exam.isActive()) {
                ArrayList<Result> results = exam.getResults();
                for (Result result : results) {
                    if (result.getStudent() == this.user) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("popUp.fxml"));
                        Scene scene = new Scene(loader.load());
                        PopUpController popUpController = loader.getController();
                        popUpController.sethaveattend();
                        Stage stage = new Stage();
                        popUpController.stage = stage;
                        stage.setScene(scene);
                        stage.show();
                        return;
                    }
                }
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
        Random random = new Random();
        int randomNum = random.nextInt(3);
        String randomColor = hexcolors.get(randomNum);
        card.setStyle(
                "-fx-background-color: " + randomColor + "; " +
                        "-fx-border-radius: 25px;"
        );

//        card.setStyle("""
//                -fx-background-color: white;
//                -fx-opacity: 50;
//                -fx-border-radius: 20px;
//                """);
        name.setText(exam.getExamTitle());
    }

    public void setLongAnswerQuestionCardWithoutAnswer(LongAnswer question) {
        questionLabel.setText(question.getQuestion());
        examPageController.questionCards.add(this);
    }

    public void setShortAnswerQuestionCardWithoutAnswer(SingleAnswer question) {
        questionLabel.setText(question.getQuestion());
        String answers = "";
        for (String answer : question.getChoices()) {
            answers += answer + "\n";
        }
        shortAnswerChoiceLabel.setText(answers);

        ObservableList<String> choiceNumbers = FXCollections.observableArrayList();
        for (int i = 1; i <= question.getCountOfChoice().getValue(); i++) {
            choiceNumbers.add(Integer.toString(i));
        }
        choiceNumbers.add("Delete answer");
        choiceComboBox.setItems(choiceNumbers);
        examPageController.questionCards.add(this);
    }

    public void setLongAnswerQuestionCardWithAnswer(LongAnswer question) {
        if(exam!=null){
            if(this.questionBankController!=null){
                plusButton.setVisible(true);
            }
            else if(this.user==this.coursePlan.getTeacher()){
                if(!this.exam.hasStarted()){
                    deleteQuestion.setVisible(true);
                }
            }

        }
        else{
            if(user.getUserType()== UserType.ADMIN){
                deleteQuestion.setVisible(true);
            }
        }
        questionLabel.setText(question.getQuestion());
        longAnswerQuestionTextField.editableProperty().set(false);
        longAnswerQuestionTextField.setText(question.getAnswer());
        if(exam.isActive())
            examPageController.questionCards.add(this);
    }

    public void setShortAnswerQuestionCardWithAnswer(SingleAnswer question) {
        if(exam!=null){
            if(this.questionBankController!=null){
                plusButton.setVisible(true);
            }
            else if(this.user==this.coursePlan.getTeacher()){
                if(!this.exam.hasStarted()){
                    deleteQuestion.setVisible(true);
                }

            }
        }
        else{
            if(user.getUserType()== UserType.ADMIN){
                deleteQuestion.setVisible(true);
            }
        }
        questionLabel.setText(question.getQuestion());
        String answers = "";
        int i = 1;
        for (String answer : question.getChoices()) {
            answers += i + "      " + answer + "\n";
            i++;
        }
        answers += "answer:     " + Integer.toHexString(question.getAnswerValue()+1);
        shortAnswerChoiceLabel.setText(answers);
        choiceComboBox.setVisible(false);
        if(exam.isActive())
            examPageController.questionCards.add(this);
    }

    public void setLongAnswerQuestionCardWithUserAnswer(LongAnswer question, String userAnswer) {
        questionLabel.setText(question.getQuestion());
        longAnswerQuestionTextField.editableProperty().set(false);
        StringBuilder answerField = new StringBuilder();
        answerField.append("answer: ").append(question.getAnswer()).append("\n");
        longAnswerQuestionTextField.setText(answerField.toString());
        StringBuilder userAnswerField = new StringBuilder();
        userAnswerField.append("Your answer: ").append(userAnswer);
        TextField textField = new TextField();
        textField.editableProperty().set(false);
        textField.setText(userAnswerField.toString());
        cardBox.getChildren().add(textField);
    }

    public void setShortAnswerQuestionCardWithUserAnswer(SingleAnswer question, String userAnswer) {
        questionLabel.setText(question.getQuestion());
        String answers = "";
        for (String answer : question.getChoices()) {
            answers += answer + "\n";
        }
        if(question.getAnswerValue()==-1){
            answers += "unanswered";
        }
        else{
            answers += "answer:     " + (question.getAnswerValue()+1);
        }
        shortAnswerChoiceLabel.setText(answers);
        TextField textField = new TextField();
        textField.setText(userAnswer);
        textField.editableProperty().set(false);
        cardBox.getChildren().get(2).setVisible(false);
        cardBox.getChildren().add(textField);
    }
    private void setCards(ArrayList<Question> questions, VBox cardVbox,boolean showWithAnswer) {
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
                    cardController.user = this.user;
                    cardController.exam =this.exam;
                    cardController.coursePlan = this.coursePlan;
                    cardController.examPageController = this.examPageController;
                    cardController.examPageVbox = cardVbox;
                    try {
                        if(showWithAnswer){
                            Result studentResult = null;
                            for (Result result:this.exam.getResults()) {
                                if(result.getStudent()==this.user)
                                    studentResult = result;
                            }
                            //shows users answers
                            if(studentResult!=null){
                                TextField textField = new TextField();
                                if (ResultController.checkMultipleChoiceAnswer(studentResult, (SingleAnswer) question)){
                                    textField.setText("True");
                                    eachQuestionBox.setStyle("-fx-background-color: rgba(62,224,62,0.3)");
                                }
                                else {
                                    textField.setText("False");
                                    eachQuestionBox.setStyle("-fx-background-color: rgba(210,33,33,0.3)");
                                }
                                textField.setEditable(false);
                                eachQuestionBox.getChildren().add(textField);
                                cardController.setShortAnswerQuestionCardWithUserAnswer((SingleAnswer) question,Integer.toString((Integer) studentResult.getAnswers().get(question)+1));
                            }
                            //if user does not have a result shows previous result
                            else {
                                cardController.setShortAnswerQuestionCardWithAnswer((SingleAnswer) question);
                            }

                        }
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
                    cardController.user = this.user;
                    cardController.coursePlan = this.coursePlan;
                    cardController.examPageController = this.examPageController;
                    cardController.examPageVbox = cardVbox;
                    try {
                        if (showWithAnswer) {
                            Result studentResult = null;
                            for (Result result:this.exam.getResults()) {
                                if(result.getStudent()==this.user)
                                    studentResult = result;
                            }
                            if(studentResult!=null){
                                TextField textField = new TextField();
                                textField.setPromptText("Score");
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
  public void setDeleteQuestion(){
        if(exam!=null){
            this.exam.getQuestions().remove(this.question);
            try {
                System.out.println(this.examPageVbox);
                examPageVbox.getChildren().clear();
                setCards(this.exam.getQuestions(),examPageVbox,true);

//                FXMLLoader loader = new FXMLLoader();
//                loader.setLocation(getClass().getResource("ExamPage.fxml"));
//                Scene scene = new Scene(loader.load());
//
//                // Now that the FXML is loaded, get the controller and set the data
//                ExamPageController examPageController = loader.getController();
//                //System.out.println(this.user);
//                examPageController.user = this.user;
//                examPageController.exam = this.exam;
//                examPageController.coursePlan = this.coursePlan;
//                examPageController.setExamPage();
//
//                HelloApplication.mainStage.setScene(scene);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else{
            //the question should be removed from question bank
            DataBase.getQuestions().get(this.course).remove(this.question);
            questionBankController.setComboBox();
        }

  }
  public void setMinusButton(){
        this.exam.getQuestions().remove(this.question);
        minusButton.setVisible(false);
        plusButton.setVisible(true);
  }
  public void setPlusButton(){
        this.exam.getQuestions().add(this.question);
        plusButton.setVisible(false);
        minusButton.setVisible(true);
  }

    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
