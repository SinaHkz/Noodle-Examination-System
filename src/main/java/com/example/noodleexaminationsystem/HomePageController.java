package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.Course.CoursePlan;
import com.example.noodleexaminationsystem.Course.Exam;
import com.example.noodleexaminationsystem.User.User;
import com.example.noodleexaminationsystem.User.UserType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    private ArrayList<CoursePlan> myCourses;
    private ArrayList<CoursePlan> teacherCourses;
    private ArrayList<CoursePlan> archivedCourses;
    User user;

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
    @FXML
    private Label username;
    @FXML
    private Button controlCenter;

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
                cardController.coursePlan = coursePlan;
                cardController.user = this.user;
                try {
                    cardController.setCourseCard(coursePlan);
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

    public void setHomePage(User user) {
        try {
            File file = new File(user.getPicturePath());
            // Create a FileInputStream from the File
            FileInputStream stream = new FileInputStream(file);
            Image newImage = new Image(stream);
            //making the picture round
            final Circle clip = new Circle(123.5, 136, 110);
            profileImage.setClip(clip);
            profileImage.setImage(newImage);
            username.setText(user.getUsername());



        } catch (Exception e) {
            // Handle exception
            //if the path can not be resolved the default picture will be shown as profile picture
            System.out.println(e);
        }

        myCourses = user.getStudentcoursePlans();
        teacherCourses = new ArrayList<>(user.getTeacherCourses().values());
        archivedCourses = user.getArchivedCoursePlans(LocalDate.now());

        //for loop on all courses
        setCards(myCourses, cardVBox);
        setCards(teacherCourses, cardVBoxTeacher);
        setCards(archivedCourses, cardVBoxArchived);

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

    public void setOptionButton() {
        option.getStyleClass().addAll("button-hand", "background-transparent", "selected-buttons");
        archiveCourseButton.getStyleClass().removeAll("selected-buttons");
        teacherCourseButton.getStyleClass().removeAll("selected-buttons");
        myCoursesButton.getStyleClass().removeAll("selected-buttons");
        cardVBox.setVisible(false);
        cardVBoxArchived.setVisible(false);
        cardVBoxTeacher.setVisible(false);
        optionBox.setVisible(true);
        System.out.println(user.getUserType());
        if (user.getUserType().compareTo(UserType.ADMIN) == 0)
            controlCenter.setVisible(true);
    }

    public void setChangePasswordButton() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChangePassword.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ChangePasswordController changePasswordController = fxmlLoader.getController();
        changePasswordController.previousUser = this.user;
        HelloApplication.mainStage.setScene(scene);
//        HelloApplication.setScene("ChangePassword.fxml");
    }

    public void setCreateNewCourseButton() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateCoursePlan.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            CreateCoursePlanController createCoursePlanController = loader.getController();
            createCoursePlanController.user = this.user;
            HelloApplication.mainStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        HelloApplication.setScene("CreateCoursePlan.fxml");
    }

    public void setLogoutButton() {
        HelloApplication.setScene("login.fxml");
    }

    public void setControlCenter() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ControlCenter.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            ControlCenterController controlCenterController = loader.getController();
            controlCenterController.previousUser = this.user;
            HelloApplication.mainStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        HelloApplication.setScene("ControlCenter.fxml");
    }

    public void setQuestionBank() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("QuestionsBank.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            QuestionBankController questionBankController = loader.getController();
            questionBankController.previousUser = this.user;
            HelloApplication.mainStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
