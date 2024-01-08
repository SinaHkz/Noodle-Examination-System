package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.User.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class ControlCenterController implements Initializable {
    public User previousUser;



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

    }
}
