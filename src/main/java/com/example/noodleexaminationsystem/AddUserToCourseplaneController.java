package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.CoursePlan;
import com.example.noodleexaminationsystem.User.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Callback;


import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddUserToCourseplaneController implements Initializable {
    public User previousUser;
    public CoursePlan coursePlan;


    @FXML
    private TextField searchedUsernameTextField;
    @FXML
    private Label usernameLabel;
    @FXML
    private Button addUserToCourserButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button searchButton;
    @FXML
    private ListView listView;
    @FXML
    private ImageView profileImage;
    @FXML
    private Button addUserButton;
    @FXML
    private Button courseUserButton;



    public void setSearchButton() {
        String inp = searchedUsernameTextField.getText();
        List<String> usernames = DataBase.usernameTrie.findIncompleteWords(inp);
        ObservableList<String> usernameObservableList = FXCollections.observableArrayList();
        for (String str : usernames)
            usernameObservableList.add(str);
        listView.setItems(usernameObservableList);
    }

    public void setAddUserToCourserButton() throws IOException {
        String username = listView.getSelectionModel().getSelectedItem().toString();
        this.coursePlan.addStudentToCoursePlane(username);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("popUp.fxml"));
        Scene scene = new Scene(loader.load());
        PopUpController popUpController = loader.getController();
        Stage stage = new Stage();
        stage.setScene(scene);
        popUpController.stage = stage;
        stage.show();
    }


    public void setAddUserButton() {
        listView.getItems().clear();
        profileImage.setVisible(false);
        usernameLabel.setVisible(false);


        addUserButton.setVisible(false);
        deleteButton.setVisible(false);

        searchedUsernameTextField.setVisible(true);
        addUserToCourserButton.setVisible(true);
        searchButton.setVisible(true);
        courseUserButton.setVisible(true);
    }

    public void setCourseUserButton() {
        addUserButton.setVisible(true);
        deleteButton.setVisible(true);

        searchedUsernameTextField.setVisible(false);
        addUserToCourserButton.setVisible(false);
        searchButton.setVisible(false);
        courseUserButton.setVisible(false);
        setScrollPane();
    }


    public void setSeeProfile() {
        String username = listView.getSelectionModel().getSelectedItem().toString();
        User student = DataBase.getUsers().get(username);
        usernameLabel.setText(username);
        usernameLabel.setVisible(true);
        try {
            FileInputStream stream = new FileInputStream(student.getPicturePath());
            Image newImage = new Image(stream);
            profileImage.setImage(newImage);
            final Circle clip = new Circle(123.5, 136, 110);
            profileImage.setClip(clip);
            profileImage.setImage(newImage);
            profileImage.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDeleteButton() {
        String username = listView.getSelectionModel().getSelectedItem().toString();
        User student = DataBase.getUsers().get(username);
        coursePlan.removeStudent(student);
        setScrollPane();
    }

    public void setScrollPane() {
        ObservableList<String> usernames = FXCollections.observableArrayList();
        for (User user : coursePlan.getStudent()) {
            usernames.add(user.getUsername());
        }
        listView.setItems(usernames);
    }

    public void setBackButton() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CoursePage.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            // Now that the FXML is loaded, get the controller and set the data
            CoursePageController coursePageController = loader.getController();
            coursePageController.user = this.previousUser;
            coursePageController.coursePlan = this.coursePlan;
            coursePageController.setCoursePlanPage(this.coursePlan);
            HelloApplication.mainStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
