package com.example.demo.controller;

public class User {
    private static Long nextId = 1L;
    private Long id;
    private String firstname;
    private String lastname;
    private String contact;

    public User(String firstname, String lastname, String contact) {
        this.id = nextId++;
        this.firstname = firstname;
        this.lastname = lastname;
        this.contact = contact;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public String getContact() {
        return contact;
    }public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
