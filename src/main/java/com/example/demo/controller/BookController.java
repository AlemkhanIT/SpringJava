package com.example.demo.controller;

import com.example.demo.service.BookDTO;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;
    @PostMapping
    public Long createBook(@RequestBody BookDTO book) {
        return bookService.createBook(book);
    }

    @GetMapping("/{bookId}")
    public BookDTO readBook(@PathVariable Long bookId) {
        return bookService.getBook(bookId);
    }

    @GetMapping
    public List<BookDTO> listBooks(@RequestParam(name = "title", required = false) String title) {
        if (title != null) {
            return bookService.listBooks(title);
        }
        return bookService.listAllBooks();
    }

    @PutMapping("/{bookId}")
    public BookDTO updateBook(@PathVariable Long bookId, @RequestBody BookDTO updatedBook) {
        return bookService.updateBook(bookId, updatedBook);
    }

    @DeleteMapping("/{bookId}")
    public void deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
    }
}
