package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.User.User;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;

import java.net.URL;
import java.util.ResourceBundle;

public class AddQuestionController implements Initializable {
    public User previousUser;


    public void setBackButton() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("QuestionsBank.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            QuestionBankController questionBankController = loader.getController();
            questionBankController.previousUser = this.previousUser;
            HelloApplication.mainStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
