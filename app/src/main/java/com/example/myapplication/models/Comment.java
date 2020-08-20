package com.example.myapplication.models;

public class Comment {
    private int id;
    private int recipe_id;
    String Username;
    String Comment;

    public Comment(int recipe_id, String username, String comment) {
        this.recipe_id = recipe_id;
        Username = username;
        Comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(int recipe_id) {
        this.recipe_id = recipe_id;
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
