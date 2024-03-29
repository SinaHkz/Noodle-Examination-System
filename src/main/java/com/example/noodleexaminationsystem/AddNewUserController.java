package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.User.Gender;
import com.example.noodleexaminationsystem.User.User;
import com.example.noodleexaminationsystem.User.UserType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddNewUserController implements Initializable {
    public User previousUser;

    @FXML
    BorderPane signupPane;
    @FXML
    TextField signUpUsername;
    @FXML
    TextField signUpPassword;
    @FXML
    TextField name;
    @FXML
    TextField lastname;
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
    @FXML
    private ComboBox comboBox;
    @FXML
    private ComboBox TypeComboBox;

    public void setAddNewUserController() throws IOException {
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
            if (dob.getValue() == null) {
                dob.setStyle("-fx-border-color: red");
                flag = true;
            }
            if (comboBox.getSelectionModel().getSelectedItem() == null) {
                comboBox.setStyle("-fx-border-color: red");
                flag = true;
            }

            if (TypeComboBox.getSelectionModel().getSelectedItem() == null) {
                TypeComboBox.setStyle("-fx-border-color: red");
                flag = true;
            }

            if (flag) return;
            if (signUpPassword.getText().length() < 8) {
                passwordLessThanEight.setVisible(true);
                return;
            }
            if (picturePath.getText().isEmpty()) {
                picturePath.setText("");
            }

            User user = User.signUp(signUpUsername.getText(), signUpPassword.getText(), name.getText(), lastname.getText(), email.getText(), picturePath.getText(), dob.getValue(), comboBox.getSelectionModel().getSelectedItem().toString(), TypeComboBox.getSelectionModel().getSelectedItem().toString());
            if (user == null) {
                usernameTaken.setVisible(true);
                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ControlCenter.fxml"));
            Scene scene = new Scene(loader.load());
            ControlCenterController controlCenterController = loader.getController();
            controlCenterController.previousUser = previousUser;
            HelloApplication.mainStage.setScene(scene);
            signupPane.setVisible(true);
        }

    }

    public void setBackButton(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ControlCenter.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            ControlCenterController controlCenterController = loader.getController();
            controlCenterController.previousUser = this.previousUser;
            HelloApplication.mainStage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList();
        ObservableList<String> types = FXCollections.observableArrayList();
        for (UserType type : UserType.values())
            types.add(type.toString());
        for (Gender gender : Gender.values())
            list.add(gender.toString());
        comboBox.setItems(list);
        TypeComboBox.setItems(types);
    }
}
