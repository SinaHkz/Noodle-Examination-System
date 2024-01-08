package com.example.noodleexaminationsystem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangePasswordController implements Initializable {


    @FXML
    TextField oldPassword;

    @FXML
    TextField newPassword;

    @FXML
    private Label passwordLessThanEight;

    @FXML
    Label incorrectPassLabel;

    public void setBackToHomePageButton(){
        HelloApplication.setScene("homePage.fxml");
    }

    public void setChangePasswordButton(){

        boolean flag = false;
        if (oldPassword.getText().isEmpty()) {
            oldPassword.setStyle("-fx-prompt-text-fill: red");
            flag = true;
        }
        if (newPassword.getText().isEmpty()) {
            newPassword.setStyle("-fx-prompt-text-fill: red");
            flag = true;
        }
        if (flag) {
            return;
        }
//        if(HelloApplication.mainUser.getPassword().equals(oldPassword.getText())){
//            if(newPassword.getText().length() < 8) {
//                passwordLessThanEight.setVisible(true);
//            }
//            else {
//                HelloApplication.mainUser.setPassword(newPassword.getText());
//                HelloApplication.setScene("homePage.fxml");
//            }
//        }
//        else {
//            incorrectPassLabel.setVisible(true);
//        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
