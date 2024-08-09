package com.example.chatapp;

public class Message {
    private String userName;
    private String messageContent;
    private long timestamp;

    public Message(String userName, String messageContent, long timestamp) {
        this.userName = userName;
        this.messageContent = messageContent;
        this.timestamp = timestamp;
    }

    public String getUserName() {
        return userName;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
