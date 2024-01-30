package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Course.Course;
import com.example.noodleexaminationsystem.Course.Exam;
import com.example.noodleexaminationsystem.User.Gender;
import com.example.noodleexaminationsystem.User.User;
import com.example.noodleexaminationsystem.User.UserType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;

public class loginPageController implements Initializable {

    @FXML
    TextField signUpUsername;
    @FXML
    TextField signUpPassword;
    @FXML
    TextField loginUsername;
    @FXML
    TextField loginPassword;
    @FXML
    TextField name;
    @FXML
    TextField lastname;
    @FXML
    BorderPane loginPane;
    @FXML
    BorderPane signupPane;
    @FXML
    Label incorrectPassLabel;

    @FXML
    private ComboBox comboBox;

    @FXML
    private DatePicker dob;
    @FXML
    private TextField email;
    @FXML
    private Label usernameTaken;
    @FXML
    private Label passwordLessThanEight;
    @FXML
    Button ChoosePicturePath;
    private String picturePath;
    public void setLoginButton() throws IOException {
        if (loginPane.isVisible()) {

            boolean flag = false;
            if (loginUsername.getText().isEmpty()) {
                loginUsername.setStyle("-fx-prompt-text-fill: red");
                flag = true;
            }
            if (loginPassword.getText().isEmpty()) {
                loginPassword.setStyle("-fx-prompt-text-fill: red");
                flag = true;
            }
            if (flag) {
                return;
            }
            User user = User.login(loginUsername.getText(), loginPassword.getText());
            if(DataBase.getUsers().get(loginUsername.getText())==null){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("popUp.fxml"));

                Scene scene = new Scene(loader.load());
                PopUpController popUpController = loader.getController();
                popUpController.setPopUpLogin();
                Stage stage = new Stage();
                popUpController.stage = stage;
                stage.setScene(scene);
                stage.show();
                return;
            }
            if (user == null) {
                incorrectPassLabel.setVisible(true);
                return;
            }
            //showing home page
//            System.out.println("issue:   "+user);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("homePage.fxml"));
            try {
                Scene scene = new Scene(loader.load());
                // Now that the FXML is loaded, get the controller and set the data
                HomePageController homePageController = loader.getController();
                homePageController.user = user;
                homePageController.setHomePage(user);
                HelloApplication.mainStage.setScene(scene);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        loginPane.setVisible(true);
        signupPane.setVisible(false);

    }
    @FXML
    private void choosePicture() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Picture");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.jpeg")
        );
        // Open FileChooser Dialog
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            picturePath = selectedFile.getAbsolutePath();
            picturePath=resizeAndSaveImage(picturePath,"src/main/resources/images",signUpUsername.getText(),272,247);
//            System.out.println(picturePath);
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


    public void setSignUpButton() {
        boolean flag = false;
        if (signupPane.isVisible()) {
            comboBox.setStyle("-fx-border-color: #096dde");
            dob.setStyle("-fx-border-color: #096dde");

            if (name.getText().isEmpty()) {
                name.setStyle("-fx-prompt-text-fill: red");
                flag = true;
            }
            if (lastname.getText().isEmpty()) {
                lastname.setStyle("-fx-prompt-text-fill: red");
                flag = true;
            }
            if (signUpUsername.getText().isEmpty()) {
                signUpUsername.setStyle("-fx-prompt-text-fill: red");
                flag = true;
            }
            if (signUpPassword.getText().isEmpty()) {
                signUpPassword.setStyle("-fx-prompt-text-fill: red");
                flag = true;
            }
            if (email.getText().isEmpty()) {
                email.setStyle("-fx-prompt-text-fill: red");
                flag = true;
            }

            if (signUpUsername.getText().isEmpty()) {
                signUpUsername.setStyle("-fx-prompt-text-fill: red");
                flag = true;
            }
            if (dob.getValue() == null) {
                dob.setStyle("-fx-border-color: red");
                flag = true;
            }
            if (comboBox.getSelectionModel().getSelectedItem() == null) {
                comboBox.setStyle("-fx-border-color: red");
                flag = true;
            }

            if (flag) return;

            if (signUpPassword.getText().length() < 8) {
                passwordLessThanEight.setVisible(true);
                return;
            }

            User user = User.signUp(signUpUsername.getText(), signUpPassword.getText(), name.getText(), lastname.getText(), email.getText(), picturePath, dob.getValue(), comboBox.getSelectionModel().getSelectedItem().toString(), "MEMBER");
            if (user == null) {
                usernameTaken.setVisible(true);
                return;
            }
            //show home page
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("homePage.fxml"));
            try {
                Scene scene = new Scene(loader.load());
                // Now that the FXML is loaded, get the controller and set the data
                HomePageController homePageController = loader.getController();
                homePageController.user = user;
                homePageController.setHomePage(user);
                HelloApplication.mainStage.setScene(scene);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        loginPane.setVisible(false);
        signupPane.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list = FXCollections.observableArrayList();
        for (Gender gender : Gender.values())
            list.add(gender.toString());
        comboBox.setItems(list);
    }

}
