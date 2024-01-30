package com.example.noodleexaminationsystem.Direct;

import com.example.noodleexaminationsystem.User.User;

public class Message {
    String message;
    User sender;
    boolean isSeen = false;


    private Message(String message, User sender) {
        this.message = message;
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public void setSeen(boolean seen) {
        isSeen = seen;
    }

    public static Message creatMessage(User sender, String message) {
        return new Message(message,sender);
    }
}
