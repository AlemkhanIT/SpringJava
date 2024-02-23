package com.example.demo.controller;

public class Book {
    private static Long nextId = 1L;

    private Long id;
    private String authorFirstname;
    private String authorLastname;
    private String title;
    private String isbn;
    private int count;

    public Book(String authorFirstname, String authorLastname, String title, String isbn, int count) {
        this.id = nextId++;
        this.authorFirstname = authorFirstname;
        this.authorLastname = authorLastname;
        this.title = title;
        this.isbn = isbn;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public String getAuthorFirstName() {
        return authorFirstname;
    }

    public String getAuthorLastName() {
        return authorLastname;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getCount() {
        return count;
    }

    public void setAuthorFirstName(String authorFirstname) {
        this.authorFirstname = authorFirstname;
    }

    public void setAuthorLastName(String authorLastname) {
        this.authorLastname = authorLastname;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setCount(int count) {
        this.count = count;
    }
}