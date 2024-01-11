package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.User.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class ControlCenterController implements Initializable {
    public User previousUser;

    @FXML
    private Button userButton;
    @FXML
    private Button questionBankButton;
    @FXML
    private Button courseButton;
    @FXML
    private Group userGroup;
    @FXML
    private Group questionBankGroup;
    @FXML
    private Group courseGroup;
    @FXML
    private ListView userList;
    @FXML
    private Button plusUser;
    @FXML
    private Label successfulDeletion;
    @FXML
    private ListView courseList;
    @FXML
    private Label successfulDeletionCourse;

    public void setBackButton() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("homePage.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            HomePageController homePageController = loader.getController();
            homePageController.user = this.previousUser;
            homePageController.setHomePage(previousUser);
            HelloApplication.mainStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        ------------------------------------set userList data------------------------------------
        ObservableList<String> usernames = FXCollections.observableArrayList();
        for (User user : DataBase.users.values()) {
            usernames.add(user.getUsername());
        }
        userList.setItems(usernames);

//        ------------------------------------set course data------------------------------------
        ObservableList<String> courses = FXCollections.observableArrayList();
        for (Course course : DataBase.courses.values()) {
            courses.add(course.getCourse());
        }
        courseList.setItems(courses);//not tested!!!!!!!!

    }

//    ------------------------------------------set page buttons---------------------------------------

    public void setPlusUserButton() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddNewUser.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            AddNewUserController addNewUserController = loader.getController();
            addNewUserController.previousUser = this.previousUser;
            HelloApplication.mainStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMinusUserButton() {
        String username = userList.getSelectionModel().getSelectedItem().toString();
        User user = DataBase.users.get(username);
        boolean isDeleted;
        if (user != this.previousUser)
            isDeleted = User.deleteUser(user);
        else {
            successfulDeletion.setVisible(false);
            return;
        }

        if (isDeleted) {
            successfulDeletion.setVisible(true);
            ObservableList<String> usernames = FXCollections.observableArrayList();
            for (User user1 : DataBase.users.values()) {
                usernames.add(user1.getUsername());
            }
            userList.setItems(usernames);
        } else
            successfulDeletion.setVisible(false);
    }

    public void setPlusCourseButton() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateCourse.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            CreateCourseController createCourseController = loader.getController();
            createCourseController.previousUser = this.previousUser;
            HelloApplication.mainStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMinusCourseButton() {
        String courseName = courseList.getSelectionModel().getSelectedItem().toString();
        Course course = DataBase.getCourses().get(courseName);
        if (course.deleteCourse()) {
            successfulDeletionCourse.setVisible(true);
            ObservableList<String> courses = FXCollections.observableArrayList();
            for (Course course1 : DataBase.getCourses().values())
                courses.add(course1.getCourse());
            courseList.setItems(courses);
        } else
            successfulDeletionCourse.setVisible(false);

    }


    public void setUserButton() {
        userButton.getStyleClass().addAll("selected-buttons");
        courseButton.getStyleClass().removeAll("selected-buttons");
        userGroup.setVisible(true);
        courseGroup.setVisible(false);
        //set deletion message invisible.
        successfulDeletion.setVisible(false);
        successfulDeletionCourse.setVisible(false);
    }

    public void setCourseGroup() {
        courseButton.getStyleClass().addAll("selected-buttons");
        userButton.getStyleClass().removeAll("selected-buttons");
        userGroup.setVisible(false);
        courseGroup.setVisible(true);
        //set deletion message invisible.
        successfulDeletion.setVisible(false);
        successfulDeletionCourse.setVisible(false);
    }

    public void setQuestionBankButton() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("QuestionsBank.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            QuestionBankController questionBankController = loader.getController();
            questionBankController.previousUser = this.previousUser;
            HelloApplication.mainStage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
