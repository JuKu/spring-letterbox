package com.jukusoft.letterbox.controller;

public class MessageDTO {

    private String sender;
    private String title;
    private String text;

    public MessageDTO(String sender, String title, String text) {
        this.sender = sender;
        this.title = title;
        this.text = text;
    }

    public String getSender() {
        return sender;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

}
