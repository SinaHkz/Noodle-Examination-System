package com.example.noodleexaminationsystem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateCoursePlanController implements Initializable {
   @FXML
   TextField courseName;
   @FXML
   TextField coursePictureAddress;
   @FXML
    ComboBox comboBox;
   public void setBckButton() {
       HelloApplication.setScene("homePage.fxml");
   }
   public void setCreateCoursePlanButton(){
       boolean flag=false;
       comboBox.setStyle("border-colored-bottom:#096dde");
        if(courseName.getText().isEmpty()){
            courseName.setStyle("-fx-prompt-text-fill: red");
            flag=true;
        }
        if(coursePictureAddress.getText().isEmpty()){
            coursePictureAddress.setStyle("-fx-prompt-text-fill: red");
            flag=true;
        }
        if(comboBox.getSelectionModel().getSelectedItem() == null){
            comboBox.setStyle("-fx-border-color: red");
            flag = true;
        }
        if(flag) return;
   }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        ObservableList<String> list = FXCollections.observableArrayList();
//        for (Course course : )
//            list.add(course.toString());
//        comboBox.setItems(list);
    }
}
