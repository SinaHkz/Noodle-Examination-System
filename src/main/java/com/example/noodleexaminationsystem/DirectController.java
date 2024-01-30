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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
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
        openDirect(user);
    }


    public void setOpenDirect() {
        String username = listView.getSelectionModel().getSelectedItem().toString();
        User student = DataBase.getUsers().get(username);
        usernameLabel.setText(username);
        usernameLabel.setVisible(true);
        try {
            FileInputStream stream = new FileInputStream(student.getPicturePath());
            Image newImage = new Image(stream);
            profileImage.setImage(newImage);
            final Circle clip = new Circle(123.5, 136, 110);
            profileImage.setClip(clip);
            profileImage.setImage(newImage);
            profileImage.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        openDirect(student);
        listView.setVisible(false);
        chatBox.setVisible(true);
        chatBox.setAlignment(Pos.BOTTOM_CENTER);
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
        sendButton.setVisible(true);

    }

    public void setSearchButton() {
        openedDirectUser = null;
        messageTextField.setVisible(false);
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
        sendButton.setVisible(false);
        openDirect.setVisible(true);
        directList.setVisible(false);
        listView.setVisible(true);
        chatBox.setVisible(false);
    }

    public void setSendButton() {
        String message = messageTextField.getText();
        messageTextField.clear();
        if (message.length() > 70)
            ;//pop up
        Direct direct = previousUser.getDirectByUser(openedDirectUser);
        Message message1 = direct.sendMessage(previousUser,message);

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
