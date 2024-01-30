package com.example.noodleexaminationsystem;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PopUpController implements Initializable {
    public Stage stage;
    @FXML
    Label text;


    public void setPopUpButton() {
        stage.close();
    }

    public void setPopUpLogin() {
        text.setText("user not found!");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
