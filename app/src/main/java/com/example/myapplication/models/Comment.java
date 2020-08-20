package com.example.myapplication.models;

public class Comment {
    String Username;
    String Comment;

    public Comment(String username, String comment) {
        Username = username;
        Comment = comment;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }
}
