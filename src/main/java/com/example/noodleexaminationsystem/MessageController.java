package com.example.noodleexaminationsystem;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class MessageController implements Initializable {
    String inputMessage;
    @FXML
    private Label messageLabel;

    public void setMessage(Pos pos) {
        messageLabel.setText(inputMessage);
        if (inputMessage.length() > 70) {
            messageLabel.setPrefWidth(messageLabel.getPrefWidth() + 70 * 7);
        } else
            messageLabel.setPrefWidth(messageLabel.getPrefWidth() + inputMessage.length() * 7.2);
        messageLabel.setAlignment(pos);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
