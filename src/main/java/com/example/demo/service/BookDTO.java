package com.example.demo.service;

import lombok.Getter;

@Getter
public class BookDTO {
    public Long id;
    public String name;
    public String author;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
