package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.Course.CoursePlan;
import com.example.noodleexaminationsystem.Course.Exam;
import com.example.noodleexaminationsystem.User.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    private ArrayList<CoursePlan> myCourses;
    private ArrayList<CoursePlan> teacherCourses;
    private ArrayList<CoursePlan> archivedCourses;
    @FXML
    private VBox cardVBox;
    @FXML
    private VBox cardVBoxTeacher;
    @FXML
    private VBox cardVBoxArchived;
    @FXML
    private Pane optionBox;
    @FXML
    private Button myCoursesButton;
    @FXML
    private Button teacherCourseButton;
    @FXML
    private Button archiveCourseButton;
    @FXML
    private Button option;
    @FXML
    private ImageView profileImage;

    private void setCards(ArrayList<CoursePlan> coursePlans, VBox cardVbox) {

        try {
            HBox eachRowBox = new HBox();
            int counter = 0;
            for (CoursePlan coursePlan : coursePlans) {
                if (counter % 3 == 0) {
                    eachRowBox = new HBox();

                    //change spacing!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    eachRowBox.setSpacing(50);
                    //change spacing!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

                    cardVbox.getChildren().add(eachRowBox);
                }
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("coursePlanCard.fxml"));
                Pane cardBox = loader.load();

                CardController cardController = loader.getController();
                try {
                    cardController.setCard(coursePlan);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                eachRowBox.getChildren().add(cardBox);
                counter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setMyCourseButton() {
        myCoursesButton.getStyleClass().addAll("button-hand", "background-transparent", "selected-buttons");
        teacherCourseButton.getStyleClass().removeAll("selected-buttons");
        archiveCourseButton.getStyleClass().removeAll("selected-buttons");
        option.getStyleClass().removeAll("selected-buttons");
        cardVBox.setVisible(true);
        cardVBoxArchived.setVisible(false);
        cardVBoxTeacher.setVisible(false);
        optionBox.setVisible(false);
    }

    public void setTeacherCourseButton() {
        teacherCourseButton.getStyleClass().addAll("button-hand", "background-transparent", "selected-buttons");
        myCoursesButton.getStyleClass().removeAll("selected-buttons");
        archiveCourseButton.getStyleClass().removeAll("selected-buttons");
        option.getStyleClass().removeAll("selected-buttons");
        cardVBox.setVisible(false);
        cardVBoxArchived.setVisible(false);
        cardVBoxTeacher.setVisible(true);
        optionBox.setVisible(false);
    }

    public void setArchivedCourseButton() {
        archiveCourseButton.getStyleClass().addAll("button-hand", "background-transparent", "selected-buttons");
        teacherCourseButton.getStyleClass().removeAll("selected-buttons");
        myCoursesButton.getStyleClass().removeAll("selected-buttons");
        option.getStyleClass().removeAll("selected-buttons");
        cardVBox.setVisible(false);
        cardVBoxArchived.setVisible(true);
        cardVBoxTeacher.setVisible(false);
        optionBox.setVisible(false);
    }

    public void setOptionButton(){
        option.getStyleClass().addAll("button-hand", "background-transparent", "selected-buttons");
        archiveCourseButton.getStyleClass().removeAll("selected-buttons");
        teacherCourseButton.getStyleClass().removeAll("selected-buttons");
        myCoursesButton.getStyleClass().removeAll("selected-buttons");
        cardVBox.setVisible(false);
        cardVBoxArchived.setVisible(false);
        cardVBoxTeacher.setVisible(false);
        optionBox.setVisible(true);
    }
    public void setChangePasswordButton(){
        HelloApplication.setScene("ChangePassword.fxml");
    }
    public void setCreateNewCourseButton(){
        HelloApplication.setScene("CreateCoursePlan.fxml");
    }
    public void setLogoutButton(){
        HelloApplication.setScene("login.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //set profile
        try {
            File file = new File(HelloApplication.mainUser.getPicturePath());
            // Create a FileInputStream from the File
            FileInputStream stream = new FileInputStream(file);
            Image newImage = new Image(stream);
            //making the picture round
            final Circle clip = new Circle(123.5,136,110);
            profileImage.setClip(clip);
            profileImage.setImage(newImage);

        } catch (Exception e) {
            // Handle exception
            //if the path can not be resolved the default picture will be shown as profile picture
            System.out.println(e);
        }

        myCourses = HelloApplication.mainUser.getStudentcoursePlans();
        teacherCourses = HelloApplication.mainUser.getTeacherCourses();
        archivedCourses = HelloApplication.mainUser.getArchivedCoursePlans(LocalDate.now());

        //for loop on all courses
        setCards(myCourses, cardVBox);
        setCards(teacherCourses, cardVBoxTeacher);
        setCards(archivedCourses, cardVBoxArchived);

    }
}
