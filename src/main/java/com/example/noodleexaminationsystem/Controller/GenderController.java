package com.example.noodleexaminationsystem.controller;

import com.example.noodleexaminationsystem.User.Gender;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GenderController implements Initializable {

    @FXML
    private ComboBox comboBox;

    @FXML
    private Label label;

    private ArrayList<String> genderList;

    @FXML
    void Select() {
        String s = comboBox.getSelectionModel().getSelectedItem().toString();
        label.setText(s);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList ("JavaFX", "SceneBuilder", "Laravel");
        comboBox.setItems(list);
    }
}
