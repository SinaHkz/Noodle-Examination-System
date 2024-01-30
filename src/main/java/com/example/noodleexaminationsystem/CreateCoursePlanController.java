package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.Course.CoursePlan;
import com.example.noodleexaminationsystem.Course.Exam;
import com.example.noodleexaminationsystem.User.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CreateCoursePlanController implements Initializable {
    public User user;

    @FXML
    TextField courseName;
    @FXML
    ComboBox comboBox;
    @FXML
    Label courseTaken;
    @FXML
    Button PicturePath;
    private String picturePath;
    @FXML
    private void PicturePath() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Picture");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.jpeg")
        );
        // Open FileChooser Dialog
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            picturePath = selectedFile.getAbsolutePath();
            picturePath=resizeAndSaveImage(picturePath,"src/main/resources/images",courseName.getText(),340,170);
            // You can now use selectedPicturePath where you need the path of the picture
        }
    }
    public static String resizeAndSaveImage(String inputImagePath, String outputDirectory, String outputFileName, int width, int height) {
        try {
            // Read the original image
            BufferedImage originalImage = ImageIO.read(new File(inputImagePath));

            // Create a new image of the desired size and type
            BufferedImage resizedImage = new BufferedImage(width, height, originalImage.getType());
            Graphics2D g2d = resizedImage.createGraphics();

            // Draw the original image into the new image
            g2d.drawImage(originalImage, 0, 0, width, height, null);
            g2d.dispose();

            // Ensure the output directory exists
            File directory = new File(outputDirectory);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Construct the complete output file path
            String fileExtension = inputImagePath.substring(inputImagePath.lastIndexOf('.') + 1);
            File outputFile = new File(directory, outputFileName + "." + fileExtension);

            // Write the resized image to the specified path
            ImageIO.write(resizedImage, fileExtension, outputFile);
            System.out.println("Resized image saved to: " + outputFile.getAbsolutePath());
            return outputFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }
    }
    public void setBckButton() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("homePage.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            HomePageController homePageController = loader.getController();
            homePageController.user = this.user;
            homePageController.setHomePage(user);
            HelloApplication.mainStage.setScene(scene);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCreateCoursePlanButton() {

        boolean flag = false;
        comboBox.setStyle("border-colored-bottom:#096dde");
        if (courseName.getText().isEmpty()) {
            courseName.setStyle("-fx-prompt-text-fill: red");
            flag = true;
        }
        if (comboBox.getSelectionModel().getSelectedItem() == null) {
            comboBox.setStyle("-fx-border-color: red");
            flag = true;
        }
        if (flag) return;
        String course = comboBox.getSelectionModel().toString();
        CoursePlan.addCoursePlan(course,courseName.getText(),user,LocalDate.now(),picturePath);
        setBckButton();

        //error
//        CoursePlan coursePlan=CoursePlan.addCoursePlan(courseName.getText(),comboBox.getSelectionModel().getSelectedItem().toString(),HelloApplication.mainUser,coursePictureAddress.getText());
//        if (coursePlan == null ){
//            courseTaken.setVisible(true);
//        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list = FXCollections.observableArrayList();
        for (Course course : DataBase.getCourses().values())
            list.add(course.getCourse());
        comboBox.setItems(list);
    }
}
