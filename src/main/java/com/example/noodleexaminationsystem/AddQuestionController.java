package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Question.LongAnswer;
import com.example.noodleexaminationsystem.User.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddQuestionController implements Initializable {
    public User previousUser;
    @FXML
    VBox LongAnswerVbox;
    @FXML
    VBox SingleVbox;
    @FXML
    VBox MultipleAnswerVbox;
    @FXML
    TextArea LongAnswerText;
    @FXML
    Label emptyLabel;
    @FXML
    Label emptyLabelAnswer;
    @FXML
    TextArea LongAnswerQuestion;
    @FXML
    CheckBox CheckBox11;
    @FXML
    CheckBox CheckBox22;
    @FXML
    CheckBox CheckBox33;
    @FXML
    CheckBox CheckBox44;
    @FXML
    CheckBox CheckBox55;
    @FXML
    CheckBox CheckBox66;
    @FXML
    CheckBox CheckBox77;
    @FXML
    CheckBox CheckBox88;
    @FXML
    CheckBox CheckBox1;
    @FXML
    CheckBox CheckBox2;
    @FXML
    CheckBox CheckBox3;
    @FXML
    CheckBox CheckBox4;
    @FXML
    CheckBox CheckBox5;
    @FXML
    CheckBox CheckBox6;
    @FXML
    CheckBox CheckBox7;
    @FXML
    CheckBox CheckBox8;
    @FXML
    TextArea TextArea1;
    @FXML
    TextArea TextArea2;
    @FXML
    TextArea TextArea3;
    @FXML
    TextArea TextArea4;
    @FXML
    TextArea TextArea5;
    @FXML
    TextArea TextArea6;
    @FXML
    TextArea TextArea7;
    @FXML
    TextArea TextArea8;
    @FXML
    TextArea TextArea11;
    @FXML
    TextArea TextArea22;
    @FXML
    TextArea TextArea33;
    @FXML
    TextArea TextArea44;
    @FXML
    TextArea TextArea55;
    @FXML
    TextArea TextArea66;
    @FXML
    TextArea TextArea77;
    @FXML
    TextArea TextArea88;

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
    public void setSubmitButton() {
        if (SingleVbox.isVisible()) {
            boolean flag = true;
            ArrayList<String> selectedChoices = new ArrayList<>();
            if (CheckBox11.isSelected()) selectedChoices.add(TextArea11.getText());
            if (CheckBox22.isSelected()) selectedChoices.add(TextArea22.getText());
            if (CheckBox33.isSelected()) selectedChoices.add(TextArea33.getText());
            if (CheckBox44.isSelected()) selectedChoices.add(TextArea44.getText());
            if (CheckBox55.isSelected()) selectedChoices.add(TextArea55.getText());
            if (CheckBox66.isSelected()) selectedChoices.add(TextArea66.getText());
            if (CheckBox77.isSelected()) selectedChoices.add(TextArea77.getText());
            if (CheckBox88.isSelected()) selectedChoices.add(TextArea88.getText());
        } else if (LongAnswerVbox.isVisible()) {
            emptyLabel.setVisible(false);
            emptyLabelAnswer.setVisible(false);
            if (LongAnswerQuestion.getText().isEmpty()) {
                emptyLabel.setVisible(true);
//                emptyLabelAnswer.setVisible(true);
//            LongAnswer longAnswer= LongAnswer.createQuestion(LongAnswerT.getText(),)
            }
        } else if (MultipleAnswerVbox.isVisible()) {
            ArrayList<String> selectedChoices = new ArrayList<>();
            if (CheckBox1.isSelected()) selectedChoices.add(TextArea1.getText());
            if (CheckBox2.isSelected()) selectedChoices.add(TextArea2.getText());
            if (CheckBox3.isSelected()) selectedChoices.add(TextArea3.getText());
            if (CheckBox4.isSelected()) selectedChoices.add(TextArea4.getText());
            if (CheckBox5.isSelected()) selectedChoices.add(TextArea5.getText());
            if (CheckBox6.isSelected()) selectedChoices.add(TextArea6.getText());
            if (CheckBox7.isSelected()) selectedChoices.add(TextArea7.getText());
            if (CheckBox8.isSelected()) selectedChoices.add(TextArea8.getText());

        }
    }
    public void setLongAnswer(){
        if (!LongAnswerVbox.isVisible()){
            LongAnswerVbox.setVisible(true);
            SingleVbox.setVisible(false);
            MultipleAnswerVbox.setVisible(false);
        }
    }
    public void setSingleAnswer(){
        if (!SingleVbox.isVisible()){
            SingleVbox.setVisible(true);
            LongAnswerVbox.setVisible(false);
            MultipleAnswerVbox.setVisible(false);
        }

    }

    public void setMultipleAnswer(){
        if (!MultipleAnswerVbox.isVisible()){
            SingleVbox.setVisible(false);
            LongAnswerVbox.setVisible(false);
            MultipleAnswerVbox.setVisible(true);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
