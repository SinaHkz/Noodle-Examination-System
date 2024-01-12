package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.Course.Exam;
import com.example.noodleexaminationsystem.User.Gender;
import com.example.noodleexaminationsystem.User.User;
import com.example.noodleexaminationsystem.User.UserType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

public class loginPageController implements Initializable {

    @FXML
    TextField signUpUsername;
    @FXML
    TextField signUpPassword;
    @FXML
    TextField loginUsername;
    @FXML
    TextField loginPassword;
    @FXML
    TextField name;
    @FXML
    TextField lastname;

    @FXML
    BorderPane loginPane;
    @FXML
    BorderPane signupPane;
    @FXML
    Label incorrectPassLabel;

    @FXML
    private ComboBox comboBox;

    @FXML
    private DatePicker dob;
    @FXML
    private TextField email;
    @FXML
    private TextField picturePath;
    @FXML
    private Label usernameTaken;
    @FXML
    private Label passwordLessThanEight;


    public void setLoginButton() throws IOException {
        if (loginPane.isVisible()) {

            boolean flag = false;
            if (loginUsername.getText().isEmpty()) {
                loginUsername.setStyle("-fx-prompt-text-fill: red");
                flag = true;
            }
            if (loginPassword.getText().isEmpty()) {
                loginPassword.setStyle("-fx-prompt-text-fill: red");
                flag = true;
            }
            if (flag) {
                return;
            }
            User user = User.login(loginUsername.getText(), loginPassword.getText());
            if (user == null) {
                incorrectPassLabel.setVisible(true);
                return;
            }
            //showing home page
//            System.out.println("issue:   "+user);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("homePage.fxml"));
            try {
                Scene scene = new Scene(loader.load());
                // Now that the FXML is loaded, get the controller and set the data
                HomePageController homePageController = loader.getController();
                homePageController.user = user;
                if(user== DataBase.users.get("teacher")){
                    System.out.println("yaaay");
                }
                homePageController.setHomePage(user);
                HelloApplication.mainStage.setScene(scene);
            } catch (Exception e) {
//                System.out.println("aaaaaaaaaaaaahhhhhhhhhhhhhhhhhhhh");
                System.out.println(e);
            }
            return;
        }
        loginPane.setVisible(true);
        signupPane.setVisible(false);

    }

    public void setSignUpButton() {
        boolean flag = false;
        if (signupPane.isVisible()) {
            comboBox.setStyle("-fx-border-color: #096dde");
            dob.setStyle("-fx-border-color: #096dde");

            if (name.getText().isEmpty()) {
                name.setStyle("-fx-prompt-text-fill: red");
                flag = true;
            }
            if (lastname.getText().isEmpty()) {
                lastname.setStyle("-fx-prompt-text-fill: red");
                flag = true;
            }
            if (signUpUsername.getText().isEmpty()) {
                signUpUsername.setStyle("-fx-prompt-text-fill: red");
                flag = true;
            }
            if (signUpPassword.getText().isEmpty()) {
                signUpPassword.setStyle("-fx-prompt-text-fill: red");
                flag = true;
            }
            if (email.getText().isEmpty()) {
                email.setStyle("-fx-prompt-text-fill: red");
                flag = true;
            }

            if (signUpUsername.getText().isEmpty()) {
                signUpUsername.setStyle("-fx-prompt-text-fill: red");
                flag = true;
            }
            if (picturePath.getText().isEmpty()) {
                picturePath.setStyle("-fx-prompt-text-fill: red");
                flag = true;
            }
            if (dob.getValue() == null) {
                dob.setStyle("-fx-border-color: red");
                flag = true;
            }
            if (comboBox.getSelectionModel().getSelectedItem() == null) {
                comboBox.setStyle("-fx-border-color: red");
                flag = true;
            }

            if (flag) return;

            if (signUpPassword.getText().length() < 8) {
                passwordLessThanEight.setVisible(true);
                return;
            }

            User user = User.signUp(signUpUsername.getText(), signUpPassword.getText(), name.getText(), lastname.getText(), email.getText(), picturePath.getText(), dob.getValue(), comboBox.getSelectionModel().getSelectedItem().toString(), "MEMBER");
            if (user == null) {
                usernameTaken.setVisible(true);
                return;
            }
            //show home page
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("homePage.fxml"));
            try {
                Scene scene = new Scene(loader.load());
                // Now that the FXML is loaded, get the controller and set the data
                HomePageController homePageController = loader.getController();
                homePageController.user = user;
                homePageController.setHomePage(user);
                HelloApplication.mainStage.setScene(scene);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        loginPane.setVisible(false);
        signupPane.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list = FXCollections.observableArrayList();
        for (Gender gender : Gender.values())
            list.add(gender.toString());
        comboBox.setItems(list);
    }

}
