package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.User.Gender;
import com.example.noodleexaminationsystem.User.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.net.URL;
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


    public User setLoginButton(){
        if (loginPane.isVisible()){

            boolean flag = false;
            if(loginUsername.getText().isEmpty()){
                loginUsername.setStyle("-fx-prompt-text-fill: red");
                flag = true;
            }
            if(loginPassword.getText().isEmpty()){
                loginPassword.setStyle("-fx-prompt-text-fill: red");
                flag = true;
            }
            if(flag){
                return null;
            }
            User user = User.login(loginUsername.getText(),loginPassword.getText());
            if(user==null){
                incorrectPassLabel.setVisible(true);
                return null;
            }
//            System.out.println(user.getUsername());
            return user;
        }

        loginPane.setVisible(true);
        signupPane.setVisible(false);
        return null;

    }

    public User setSignUpButton(){
        boolean flag = false;
        if (signupPane.isVisible()){
            comboBox.setStyle("-fx-border-color: #096dde");
            dob.setStyle("-fx-border-color: #096dde");

            if(name.getText().isEmpty()){
                name.setStyle("-fx-prompt-text-fill: red");
                flag = true;
            }
            if(lastname.getText().isEmpty()){
                lastname.setStyle("-fx-prompt-text-fill: red");
                flag = true;
            }
            if(signUpUsername.getText().isEmpty()){
                signUpUsername.setStyle("-fx-prompt-text-fill: red");
                flag = true;
            }
            if(signUpPassword.getText().isEmpty()){
                signUpPassword.setStyle("-fx-prompt-text-fill: red");
                flag = true;
            }
            if(email.getText().isEmpty()){
                email.setStyle("-fx-prompt-text-fill: red");
                flag = true;
            }

            if(signUpUsername.getText().isEmpty()){
                signUpUsername.setStyle("-fx-prompt-text-fill: red");
                flag = true;
            }
            if(picturePath.getText().isEmpty()){
                picturePath.setStyle("-fx-prompt-text-fill: red");
                flag = true;
            }
            if(dob.getValue()==null){
                dob.setStyle("-fx-border-color: red");
                flag = true;
            }
            if(comboBox.getSelectionModel().getSelectedItem()==null){
                comboBox.setStyle("-fx-border-color: red");
                flag = true;
            }

            if(flag)
                return null;

            if(signUpPassword.getText().length()<8){
                passwordLessThanEight.setVisible(true);
                return null;
            }

            System.out.println(dob.getValue());
            User user = User.signUp(signUpUsername.getText(),signUpPassword.getText(),name.getText(),lastname.getText() , email.getText(),picturePath.getText(),dob.getValue(),comboBox.getSelectionModel().getSelectedItem().toString());
            if(user==null){
                usernameTaken.setVisible(true);
                return null;
            }
            return user;
        }
        loginPane.setVisible(false);
        signupPane.setVisible(true);
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list = FXCollections.observableArrayList();
        for (Gender gender : Gender.values())
            list.add(gender.toString());
        comboBox.setItems(list);
    }

}
