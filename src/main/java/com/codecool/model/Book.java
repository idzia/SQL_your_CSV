package com.codecool.model;

public class Book {

    private String id;

    private String author;

    private String title;

    public Book() {}

    public Book(String id, String author, String title) {
        this.id = id;
        this.author = author;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toString() {
        return getId() + " " + getAuthor() + "-> '" + getTitle() + "'";
    }

}

