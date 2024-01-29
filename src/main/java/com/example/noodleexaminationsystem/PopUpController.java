package com.example.noodleexaminationsystem;


import javafx.fxml.Initializable;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class PopUpController implements Initializable {
    public Stage stage;

    public void setPopUpButton(){
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
