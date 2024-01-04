package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.User.Gender;
import com.example.noodleexaminationsystem.User.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            LocalDate date = LocalDate.parse("2007-12-03");
            User admin = User.signUp("admin","admin","admin","admin","jfeij","jfeeijf",date,"FEMALE");

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.setMinWidth(1920);
            stage.setMinHeight(1080);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}