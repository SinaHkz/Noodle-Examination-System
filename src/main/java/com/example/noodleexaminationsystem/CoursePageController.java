package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.CoursePlan;
import com.example.noodleexaminationsystem.Course.Exam;
import com.example.noodleexaminationsystem.User.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class CoursePageController implements Initializable {
    User user;
    //each exam and the card controllers of the questions that have been previously set
    CoursePlan coursePlan;
    @FXML
    Label teacherName;
    @FXML
    Label courseName;
    @FXML
    VBox activeExamsVbox;
    @FXML
    VBox archivedExamsVbox;
    @FXML
    Pane noExamPane1;
    @FXML
    Pane noExamPane2;
    @FXML
    Button archivedExams;
    @FXML
    Button activeExams;
    @FXML
    Group archiveCourseGroup;
    @FXML
    Group createExamButton;
    @FXML
    Button userManagement;
    @FXML
    ImageView courseImage;

    @FXML
    private void setCreateExamButton() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CreateExam.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            // Now that the FXML is loaded, get the controller and set the data
            CreateExamController createExamController = loader.getController();
            createExamController.coursePlan = this.coursePlan;
            createExamController.user = this.user;
            HelloApplication.mainStage.setScene(scene);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @FXML
    private void setArchivedExamsButton() {

        activeExamsVbox.setVisible(false);
        archivedExamsVbox.setVisible(true);
        activeExams.getStyleClass().removeAll("selected-buttons");
        archivedExams.getStyleClass().addAll("selected-buttons");
    }

    @FXML
    private void setActiveExamsButton() {
        activeExamsVbox.setVisible(true);
        archivedExamsVbox.setVisible(false);
        archivedExams.getStyleClass().removeAll("selected-buttons");
        activeExams.getStyleClass().addAll("selected-buttons");
    }

    private void setCards(ArrayList<Exam> exams, VBox cardVbox) {
        try {
            HBox eachRowBox = new HBox();
            int counter = 0;
            int count = 0;
            for (Exam exam : exams) {
                if (counter % 3 == 0) {
                    eachRowBox = new HBox();
                    eachRowBox.setSpacing(103);
                    cardVbox.getChildren().add(count++, eachRowBox);
                }
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("ExamCard.fxml"));
                Pane cardBox = loader.load();
                CardController cardController = loader.getController();
                cardController.user = this.user;
                cardController.exam = exam;
                cardController.coursePlan = this.coursePlan;
                try {
                    cardController.setExamCard(exam);
                } catch (Exception e) {
                    System.out.println(e);
                }
                eachRowBox.getChildren().add(cardBox);
                counter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCoursePlanPage(CoursePlan coursePlan) {
        if (user == coursePlan.getTeacher())
            userManagement.setVisible(true);
        //setting exam cards
        if ((coursePlan.getEnd() != null) || (coursePlan.getTeacher() != this.user)) {
            archiveCourseGroup.setVisible(false);
            createExamButton.setVisible(false);

        }
        ArrayList<Exam> activeExams = coursePlan.getActiveExams();
        ArrayList<Exam> archivedExams = coursePlan.getArchivedExams();
        if (activeExams.isEmpty())
            noExamPane1.setVisible(true);
        if (archivedExams.isEmpty())
            noExamPane2.setVisible(true);

        setCards(activeExams, activeExamsVbox);
        setCards(archivedExams, archivedExamsVbox);

        try {
            teacherName.setText(coursePlan.getTeacher().getName());
            courseName.setText(coursePlan.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            courseName.setText(coursePlan.getName());
            File file = new File(coursePlan.getPicturePath());
            // Create a FileInputStream from the File
            FileInputStream stream = new FileInputStream(file);
            Image newImage = new Image(stream);
            //making the picture round
            final Circle clip = new Circle(123.5, 136, 110);
            courseImage.setClip(clip);
            courseImage.setImage(newImage);

        } catch (Exception e) {
            // Handle exception
            //if the path can not be resolved the default picture will be shown as profile picture
            e.printStackTrace();
        }
    }

    public void setArchiveCourseButton() {

        this.coursePlan.setEnd(LocalDate.now());
        //pop up to ask if user really wants to archive the course
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //going back to home page
        setBackButton();

    }


    public void setUserManagementButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddStudentToCoursePlane.fxml"));
            Scene scene = new Scene(loader.load());
            AddUserToCourseplaneController addUserToCourseplaneController = loader.getController();
            addUserToCourseplaneController.previousUser = this.user;
            addUserToCourseplaneController.coursePlan = this.coursePlan;
            addUserToCourseplaneController.setScrollPane();
            HelloApplication.mainStage.setScene(scene);
            addUserToCourseplaneController.setScrollPane();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBackButton() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("homePage.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            // Now that the FXML is loaded, get the controller and set the data
            HomePageController homePageController = loader.getController();
            System.out.println(user);
            homePageController.user = this.user;
            homePageController.setHomePage(this.user);
            HelloApplication.mainStage.setScene(scene);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
