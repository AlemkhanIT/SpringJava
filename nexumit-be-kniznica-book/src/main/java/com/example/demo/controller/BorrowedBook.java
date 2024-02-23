package com.example.demo.controller;

public class BorrowedBook {
    private static Long nextId = 1L;
    private Long id;
    private User user;
    private Book book;

    public BorrowedBook(User user, Book book) {
        this.id = nextId++;
        this.user = user;
        this.book = book;
    }
    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }
}
