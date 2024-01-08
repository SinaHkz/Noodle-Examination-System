package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.User.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChangePasswordController implements Initializable {
    public User previousUser;

    @FXML
    TextField oldPassword;

    @FXML
    TextField newPassword;

    @FXML
    private Label passwordLessThanEight;

    @FXML
    Label incorrectPassLabel;

    public void setBackToHomePageButton() throws IOException {
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

//        HelloApplication.setScene("homePage.fxml");
    }

    public void setChangePasswordButton() {

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
//        if (HelloApplication.mainUser.getPassword().equals(oldPassword.getText())) {
//            if (newPassword.getText().length() < 8) {
//                passwordLessThanEight.setVisible(true);
//            } else {
//                HelloApplication.mainUser.setPassword(newPassword.getText());
//                HelloApplication.setScene("homePage.fxml");
//            }
//        } else {
//            incorrectPassLabel.setVisible(true);
//        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
