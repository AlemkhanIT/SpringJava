package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    public static final List<Book> books = new ArrayList<>();

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        books.add(book);
        return book;
    }

    @GetMapping
    public List<Book> listBooks(@RequestParam(name = "title", required = false) String title) {
        if (title != null) {
            return books.stream()
                    .filter(b -> b.getTitle().equalsIgnoreCase(title))
                    .collect(Collectors.toList());
        }
        return books;
    }

    @GetMapping("/{bookId}")
    public Book readBook(@PathVariable Long bookId) {
        return books.stream()
                .filter(b -> b.getId().equals(bookId))
                .findFirst()
                .orElse(null);
    }

    @PutMapping("/{bookId}")
    public Book updateBook(@PathVariable Long bookId, @RequestBody Book updatedBook) {
        Book existingBook = books.stream()
                .filter(b -> b.getId().equals(bookId))
                .findFirst()
                .orElse(null);

        if (existingBook != null) {
            existingBook.setAuthorFirstName(updatedBook.getAuthorFirstName());
            existingBook.setAuthorLastName(updatedBook.getAuthorLastName());
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setIsbn(updatedBook.getIsbn());
            existingBook.setCount(updatedBook.getCount());
        }

        return existingBook;
    }

    @DeleteMapping("/{bookId}")
    public void deleteBook(@PathVariable Long bookId) {
        books.removeIf(b -> b.getId().equals(bookId));
    }
}
