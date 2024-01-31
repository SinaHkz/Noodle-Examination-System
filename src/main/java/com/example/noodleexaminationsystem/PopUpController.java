package com.example.noodleexaminationsystem;


import com.example.noodleexaminationsystem.Direct.MediaMessage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.net.URL;
import com.example.noodleexaminationsystem.Course.CoursePlan;
import com.example.noodleexaminationsystem.User.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import java.util.ResourceBundle;

public class PopUpController implements Initializable {
    public Stage stage;
    public CoursePlan coursePlan;
    public User user;
    public VBox courseMediaVbox;
    public VBox directPageVbox;
    @FXML
    Label text;
    @FXML
    Button addMediaButton;
    @FXML
    TextField subjectField;

    public void setPopUpButton() {
        stage.close();
    }

    public void setPopUpLogin() {
        text.setText("user not found!");
    }

    public void setnotActive() {
        text.setText("Exam isn't active");
    }
    public void sethaveattend(){
        text.setText("you've attended exam");
    }

    //setting add course media pop up
    public void setCourseMediaCards(ArrayList<CoursePlan.CoursePlanMedia> coursePlanMediaArrayList, VBox cardVbox){
        try {
            for (CoursePlan.CoursePlanMedia media :coursePlanMediaArrayList ) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("fileCard.fxml"));
                HBox cardBox = loader.load();
                CardController cardController = loader.getController();
                cardController.media = media;
                cardController.user = this.user;
                cardController.coursePlan = this.coursePlan;
                try {
                    cardController.setCourseMediaCard(media);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                cardVbox.getChildren().add(cardBox);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setAddMediaButton(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Movie File");
        // Set extension filters for movie files
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Movie Files","*.wav","*.zip","*.png","*.jpg","*.jpeg","*.pdf", "*.mp4", "*.avi", "*.mkv");
        fileChooser.getExtensionFilters().add(filter);

        // Show the open dialog and wait for a movie file to be selected
        File sourceMovieFile = fileChooser.showOpenDialog(null); // Pass a reference to your stage if available

        if (sourceMovieFile != null) {
            // Define the target directory where you want to copy the movie
            String targetDirectoryPath = "src/main/resources/media//"; // Replace with the actual path
            File targetDirectory = new File(targetDirectoryPath);
            File targetMovieFile = new File(targetDirectory, sourceMovieFile.getName());

            // Ensure the target directory exists
            if (!targetDirectory.exists()) {
                targetDirectory.mkdirs();
            }
            try {
                // Copy the selected movie file to the target directory
                Files.copy(sourceMovieFile.toPath(), targetMovieFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }catch (Exception e){
                e.printStackTrace();
            }
            if(directPageVbox!=null){
                System.out.println(subjectField.getText());
                System.out.println(targetMovieFile.getAbsolutePath());
                MediaMessage mediaMessage = new MediaMessage(subjectField.getText(),this.user ,targetMovieFile.getAbsolutePath());
                System.out.println(mediaMessage);
                setMessageMediaCards(mediaMessage,directPageVbox);
                stage.close();
            }else {

                try {
                    //make a file card and add the path to it and add the card to the vbox
                    System.out.println(subjectField.getText());
                    System.out.println(targetMovieFile.getAbsolutePath());
                    this.coursePlan.addMediaToCourse(subjectField.getText(), targetMovieFile.getAbsolutePath());
                    //setting page again
                    courseMediaVbox.getChildren().clear();
                    setCourseMediaCards(coursePlan.getMedia(), this.courseMediaVbox);
                    stage.close();
                    // Optionally, play the movie using KMPlayer
                    // playMovieWithKMPlayer(targetMovieFile.getAbsolutePath());
                } catch (Exception e) {
                    e.printStackTrace();
                    // Handle the exception (e.g., show an error dialog)
                }
            }
        }
    }
    public void setMessageMediaCards(MediaMessage message, VBox chatBox){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("MediaMessage.fxml"));
            HBox cardBox = loader.load();
            CardController attachmentCard = loader.getController();
            try {
                attachmentCard.attachmentMedia = message;
                attachmentCard.setAttachmentCard();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(this.user==message.getSender()){
                cardBox.setAlignment(Pos.CENTER_RIGHT);
            }
            else{
                cardBox.setAlignment(Pos.CENTER_LEFT);
            }
            chatBox.getChildren().add(cardBox);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
