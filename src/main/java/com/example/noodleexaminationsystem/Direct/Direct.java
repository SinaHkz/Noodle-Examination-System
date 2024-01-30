package com.example.noodleexaminationsystem.Direct;

import com.example.noodleexaminationsystem.User.User;

import java.util.ArrayList;

public class Direct {
    User user1;
    User user2;
    ArrayList<Message> messages = new ArrayList<>();

    public Direct(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public static Direct creatNewDirect(User user1, User user2) {
        for (Direct direct : user1.getDirects())
            if (direct.user1 == user1 && direct.user2 == user2 || direct.user1 == user2 && direct.user2 == user1)
                return null;
        Direct direct = new Direct(user1, user2);
        user1.getDirects().add(direct);
        user2.getDirects().add(direct);
        return direct;
    }


    public Message sendMessage(User user, String message) {
        Message message1 = Message.creatMessage(user, message);
        this.messages.add(message1);
        return message1;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
}
