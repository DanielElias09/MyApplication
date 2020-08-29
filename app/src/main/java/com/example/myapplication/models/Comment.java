package com.example.myapplication.models;

public class Comment {
    private Long id;
    private int recipe_id;
    private String Username;
    private String Comment;

    public Comment(int recipe_id, String username, String comment) {
        this.recipe_id = recipe_id;
        Username = username;
        Comment = comment;
    }



    public Comment(Long id, int recipe_id, String username, String comment) {
        this(recipe_id, username, comment);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
