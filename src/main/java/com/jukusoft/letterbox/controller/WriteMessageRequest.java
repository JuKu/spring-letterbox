package com.jukusoft.letterbox.controller;

public class WriteMessageRequest {

    private long receiverID;
    private boolean anonym;
    private String title;
    private String text;

    public WriteMessageRequest(long receiverID, boolean anonym, String title, String text) {
        this.receiverID = receiverID;
        this.anonym = anonym;
        this.title = title;
        this.text = text;
    }

    public long getReceiverID() {
        return receiverID;
    }

    public boolean isAnonym() {
        return anonym;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

}
