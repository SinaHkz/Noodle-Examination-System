package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.CoursePlan;
import com.example.noodleexaminationsystem.Course.Exam;
import com.example.noodleexaminationsystem.Question.*;
import com.example.noodleexaminationsystem.User.Result;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CardController implements Initializable {
    public CoursePlan coursePlan;
    public User user;
    public Question question;
    public Exam exam;
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
    private TextField longAnswerQuestionTextField;
    @FXML
    private  Label shortAnswerChoiceLabel;
    @FXML
    private ComboBox choiceComboBox;
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

    public TextField getLongAnswerQuestionTextField() {
        return longAnswerQuestionTextField;
    }

    public void setLongAnswerQuestionTextField(TextField longAnswerQuestionTextField) {
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
        }catch (Exception e){
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
            if((!this.exam.hasStarted()) && (this.user!=this.coursePlan.getTeacher())){
                //set a label to show that the exam is yet to be started
                // <<<<<<<<<<<<<<-------------------------------
                return;
            }
            //check if the exam is active and user already has a result for that exam not to allow enter
            if(exam.isActive()){
                ArrayList<Result> results = exam.getResults();
                for (Result result: results) {
                    if(result.getStudent()==this.user){
                        //show a label that the person can not enter the exam again before time is finished
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
//        card.setStyle("""
//                -fx-background-color: white;
//                -fx-opacity: 50;
//                -fx-border-radius: 20px;
//                """);
        name.setText(exam.getExamTitle());
    }
    public void setLongAnswerQuestionCardWithoutAnswer(LongAnswer question){
        questionLabel.setText(question.getQuestion());
        examPageController.questionCards.add(this);
    }
    public void setShortAnswerQuestionCardWithoutAnswer(SingleAnswer question){
        questionLabel.setText(question.getQuestion());
        String answers = "";
        for (String answer: question.getChoices()) {
            answers += answer+ "\n";
        }
        shortAnswerChoiceLabel.setText(answers);
        ObservableList<String> choiceNumbers = FXCollections.observableArrayList();
        for(int i=1;i<=question.getCountOfChoice().getValue();i++){
            choiceNumbers.add(Integer.toString(i));
        }
        choiceNumbers.add("Delete answer");
        choiceComboBox.setItems(choiceNumbers);
        examPageController.questionCards.add(this);
    }
    public void setLongAnswerQuestionCardWithAnswer(LongAnswer question){
        questionLabel.setText(question.getQuestion());
        longAnswerQuestionTextField.editableProperty().set(false);
        longAnswerQuestionTextField.setText(question.getAnswer());
        examPageController.questionCards.add(this);
    }
    public void setShortAnswerQuestionCardWithAnswer(SingleAnswer question){
        questionLabel.setText(question.getQuestion());
        String answers = "";
        int i = 1;
        for (String answer: question.getChoices()) {
            answers += i +"      "+answer+ "\n";
            i++;
        }
        answers+= "answer:     "+ Integer.toHexString(question.getAnswerValue());
        shortAnswerChoiceLabel.setText(answers);
        choiceComboBox.setVisible(false);
        examPageController.questionCards.add(this);
    }

    //delete answer option for single answers in a combo box
    public void setComboBoxDeleteAnswer(){
        String selectedOption = choiceComboBox.getValue().toString();
        if(selectedOption.equals("Delete answer")){
//          this method does not change  the text
//            choiceComboBox.setValue("Answer");
        }
    }
    //_____________________________________________   buttons   ____________________________________________
//    public void setQuestionSubmitButton(){
//        Object answer = null;
//        if(this.question instanceof SingleAnswer){
//            //check what is answers type
//            //make sure that the user wont be able to submit question without a selected item
//            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//            answer = choiceComboBox.getSelectionModel().getSelectedItem().toString();
//            card.visibleProperty().set(true);
//            card.setStyle("-fx-background-color: #9bc29b");
//        }
//        else if(this.question instanceof LongAnswer){
//            String answerString = longAnswerQuestionTextField.getText();
//            answer = new longAnswerStudentAnswer(answerString,0);
//
//            //this is awful but put something similar to show that a question has been submitted
////            BackgroundFill backgroundFill = new BackgroundFill(Color.GREEN, null, null);
////
////            // Create a Background with the BackgroundFill
////            Background background = new Background(backgroundFill);
////
////            questionHBox.setBackground(background);
//
//        }
//        //make delete answer button visible
//        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//        examPageController.answers.put(this.question,answer);
//    }
    //add a button to remove answer of a question


    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//    public void setDeleteAnswerButton(){
//        examPageController.answers.put(this.question,null);
//        //make the delete button invisible
//    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
