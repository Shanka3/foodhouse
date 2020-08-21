package com.example.foodhouse.model;

public class Comment {

    private String userComments;
    private String publisherID;
    private String publisherName;

    public Comment() {
    }

    public Comment(String userComments, String publisherID, String publisherName) {
        this.userComments = userComments;
        this.publisherID = publisherID;
        this.publisherName = publisherName;
    }

    public String getUserComments() {
        return userComments;
    }

    public String getPublisherID() {
        return publisherID;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setUserComments(String userComments) {
        this.userComments = userComments;
    }

    public void setPublisherID(String publisherID) {
        this.publisherID = publisherID;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
}
