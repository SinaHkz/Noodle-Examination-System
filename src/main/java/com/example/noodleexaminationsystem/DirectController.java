package com.example.noodleexaminationsystem;

import com.example.noodleexaminationsystem.Direct.Direct;
import com.example.noodleexaminationsystem.Direct.Message;
import com.example.noodleexaminationsystem.User.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DirectController implements Initializable {
    public User previousUser;
    public User openedDirectUser;

    @FXML
    private ListView listView;
    @FXML
    private TextField searchedUsernameTextField;
    @FXML
    private Label usernameLabel;
    @FXML
    private ImageView profileImage;
    @FXML
    private Button openDirect;
    @FXML
    private Button directList;
    @FXML
    private VBox chatBox;
    @FXML
    private TextField messageTextField;
    @FXML
    private Button sendButton;
    @FXML
    private Label email;
    @FXML
    private Label age;
    @FXML
    private VBox profileVbox;
    @FXML
    private Button attachment;


    public void setListView() {
        ObservableList list = FXCollections.observableArrayList();
        for (Direct direct : previousUser.getDirects()) {
            System.out.println(direct);
            if (previousUser == direct.getUser1())
                list.add(direct.getUser2().getUsername());
            else
                list.add(direct.getUser1().getUsername());
        }

        listView.setItems(list);
    }

    public void setCreateDirect() {
        String username = listView.getSelectionModel().getSelectedItem().toString();
        if (username == null)
            return;
        openDirect.setVisible(false);
        directList.setVisible(true);
        User user = DataBase.users.get(username);
        Direct.creatNewDirect(previousUser, user);
        setOpenDirect();
    }


    public void setOpenDirect() {
        String username = listView.getSelectionModel().getSelectedItem().toString();
        User student = DataBase.getUsers().get(username);

        usernameLabel.setText("Username: " + username);
        email.setText("Email: " + DataBase.getUsers().get(username).getEmail());
        age.setText("Age: " + Integer.toString(DataBase.getUsers().get(username).getAge()));
        profileVbox.setVisible(true);
        try {
            System.out.println(student.getPicturePath());
            profileImage.setVisible(true);
            FileInputStream stream = new FileInputStream(student.getPicturePath());
            Image newImage = new Image(stream);
            profileImage.setImage(newImage);
            final Circle clip = new Circle(123.5, 136, 110);
            profileImage.setClip(clip);
            profileImage.setImage(newImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        chatBox.setAlignment(Pos.BOTTOM_CENTER);
        openDirect(student);
        listView.setVisible(false);
        chatBox.setVisible(true);
    }

    private void openDirect(User user) {
        openDirect.setVisible(false);
        directList.setVisible(true);
        chatBox.getChildren().clear();
        openedDirectUser = user;
        List<Message> directMessage = previousUser.getDirectByUser(user).getMessages();
        for (Message message : directMessage) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Message.fxml"));
            try {
                HBox hBox = loader.load();
                MessageController messageController = loader.getController();
                System.out.println(messageController);
                messageController.inputMessage = message.getMessage();
                if (message.getSender() == user) {
                    hBox.setAlignment(Pos.CENTER_LEFT);
                    messageController.setMessage(Pos.CENTER);
                } else {
                    hBox.setAlignment(Pos.CENTER_RIGHT);
                    messageController.setMessage(Pos.CENTER);
                }
                chatBox.getChildren().add(hBox);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        chatBox.setVisible(true);
        listView.setVisible(false);
        messageTextField.setVisible(true);
        attachment.setVisible(true);
        sendButton.setVisible(true);

    }

    public void setSearchButton() {
        openedDirectUser = null;
        messageTextField.setVisible(false);
        attachment.setVisible(false);
        sendButton.setVisible(false);
        String inp = searchedUsernameTextField.getText();
        List<String> usernames = DataBase.usernameTrie.findIncompleteWords(inp);
        ObservableList<String> usernameObservableList = FXCollections.observableArrayList();
        for (String str : usernames) {
            if (DataBase.getUsers().get(usernames) != previousUser)
                usernameObservableList.add(str);
        }
        listView.setItems(usernameObservableList);
        openDirect.setVisible(false);
        directList.setVisible(true);
        listView.setVisible(true);
        chatBox.setVisible(false);
    }

    public void setDirectList() {
        openedDirectUser = null;
        setListView();
        messageTextField.setVisible(false);
        attachment.setVisible(false);
        sendButton.setVisible(false);
        openDirect.setVisible(true);
        directList.setVisible(false);
        listView.setVisible(true);
        chatBox.setVisible(false);
        profileImage.setVisible(false);
        profileVbox.setVisible(false);
    }

    public void setSendButton() {
        String message = messageTextField.getText();
        messageTextField.clear();
        if (message.length() > 70)
            ;//pop up
        Direct direct = previousUser.getDirectByUser(openedDirectUser);
        Message message1 = direct.sendMessage(previousUser, message);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Message.fxml"));
        try {
            HBox hBox = loader.load();
            MessageController messageController = loader.getController();
            System.out.println(messageController);
            messageController.inputMessage = message1.getMessage();
            if (message1.getSender() == openedDirectUser) {
                hBox.setAlignment(Pos.CENTER_LEFT);
                messageController.setMessage(Pos.CENTER);
            } else {
                hBox.setAlignment(Pos.CENTER_RIGHT);
                messageController.setMessage(Pos.CENTER);
            }
            chatBox.getChildren().add(hBox);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void setAddMedia(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddMediaPopUp.fxml"));
        try{
            Scene scene = new Scene(loader.load());
            PopUpController popUpController = loader.getController();
            //setting pop up
            Stage stage = new Stage();
            popUpController.stage = stage;
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setBackButton() {
        openedDirectUser = null;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("homePage.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            // Now that the FXML is loaded, get the controller and set the data
            HomePageController homePageController = loader.getController();
            System.out.println(previousUser);
            homePageController.user = this.previousUser;
            homePageController.setHomePage(this.previousUser);
            HelloApplication.mainStage.setScene(scene);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void setAttachmentButton(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddMediaPopUp.fxml"));
        try{
            Scene scene = new Scene(loader.load());
            PopUpController popUpController = loader.getController();
            popUpController.directPageVbox = this.chatBox;
            popUpController.user = this.previousUser;
            //popUpController.text.setText("Please enter your message");
            //setting pop up
            Stage stage = new Stage();
            popUpController.stage = stage;
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
