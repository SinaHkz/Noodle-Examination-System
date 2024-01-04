package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.Course.CoursePlan;
import com.example.noodleexaminationsystem.Course.Exam;
import com.example.noodleexaminationsystem.User.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
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
    private Button myCoursesButton;
    @FXML
    private Button teacherCourseButton;
    @FXML
    private Button archiveCourseButton;


    public static void setHomePageScene(User user) {
        HelloApplication.setArchivedCourses(user.getArchivedCoursePlans(LocalDate.now()));
        HelloApplication.setMyCourses(user.getStudentcoursePlans());
        HelloApplication.setTeacherCourses(user.getTeacherCourses());
        HelloApplication.setScene("homePage.fxml");
    }

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
                HBox cardBox = loader.load();
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
        myCoursesButton.getStyleClass().addAll("button-hand", "background-transparent", "top-selected-buttons");
        teacherCourseButton.getStyleClass().clear();
        archiveCourseButton.getStyleClass().clear();
        cardVBox.setVisible(true);
        cardVBoxArchived.setVisible(false);
        cardVBoxTeacher.setVisible(false);
    }

    public void setTeacherCourseButton() {
        teacherCourseButton.getStyleClass().addAll("button-hand", "background-transparent", "top-selected-buttons");
        myCoursesButton.getStyleClass().clear();
        archiveCourseButton.getStyleClass().clear();
        cardVBox.setVisible(false);
        cardVBoxArchived.setVisible(false);
        cardVBoxTeacher.setVisible(true);
    }

    public void setArchivedCourseButton() {
        archiveCourseButton.getStyleClass().addAll("button-hand", "background-transparent", "top-selected-buttons");
        teacherCourseButton.getStyleClass().clear();
        myCoursesButton.getStyleClass().clear();
        cardVBox.setVisible(false);
        cardVBoxArchived.setVisible(true);
        cardVBoxTeacher.setVisible(false);
    }
    public void setLogoutButton(){
        HelloApplication.setScene("login.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        myCourses = HelloApplication.getMyCourses();
        teacherCourses = HelloApplication.getTeacherCourses();
        archivedCourses = HelloApplication.getArchivedCourses();

        //for loop on all courses
        setCards(myCourses, cardVBox);
        setCards(teacherCourses, cardVBoxTeacher);
        setCards(archivedCourses, cardVBoxArchived);

    }
}
