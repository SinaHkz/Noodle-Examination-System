package com.example.noodleexaminationsystem.Direct;

import com.example.noodleexaminationsystem.User.User;

public class MediaMessage extends Message{
    private String mediaPath;

    public MediaMessage(String message, User sender, String mediaPath) {
        super(message, sender);
        this.mediaPath = mediaPath;
    }

    public String getMediaPath() {
        return mediaPath;
    }

    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }
}
