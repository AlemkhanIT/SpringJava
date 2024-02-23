package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/borrowings")
public class BorrowedBookController {

    private static final List<BorrowedBook> borrowedBooks = new ArrayList<>();

    @PostMapping
    public BorrowedBook createBorrowing(@RequestBody CreateBorrowingRequest request) {
        User user = getUserById(request.getCustomerId());
        Book book = getBookById(request.getBookId());

        if (user != null && book != null) {
            BorrowedBook borrowedBook = new BorrowedBook(user, book);
            borrowedBooks.add(borrowedBook);
            return borrowedBook;
        }

        return null; // Handle case when user or book doesn't exist
    }

    @GetMapping
    public List<BorrowedBookResponse> listBorrowings() {
        return borrowedBooks.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{borrowingId}")
    public BorrowedBookResponse readBorrowing(@PathVariable Long borrowingId) {
        return borrowedBooks.stream()
                .filter(bb -> bb.getId().equals(borrowingId))
                .map(this::mapToResponse)
                .findFirst()
                .orElse(null);
    }
    @DeleteMapping("/{borrowingId}")
    public void returnBook(@PathVariable Long borrowingId) {
        borrowedBooks.removeIf(bb -> bb.getId().equals(borrowingId));
    }

    // Additional method to get User by ID
    private User getUserById(Long userId) {
        return UserController.users.stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    // Additional method to get Book by ID
    private Book getBookById(Long bookId) {
        return BookController.books.stream()
                .filter(b -> b.getId().equals(bookId))
                .findFirst()
                .orElse(null);
    }
    private BorrowedBookResponse mapToResponse(BorrowedBook borrowedBook) {
        BorrowedBookResponse response = new BorrowedBookResponse();
        response.setId(borrowedBook.getId());
        response.setCustomerId(borrowedBook.getUser().getId());
        response.setCustomerName(borrowedBook.getUser().getFirstName() + borrowedBook.getUser().getLastName());
        response.setBookId(borrowedBook.getBook().getId());
        response.setAuthorName(borrowedBook.getBook().getAuthorFirstName() + borrowedBook.getBook().getAuthorLastName());
        response.setTitle(borrowedBook.getBook().getTitle());
        return response;
    }

    static class BorrowedBookResponse {
        private Long id;
        private Long customerId;
        private String customerName;
        private Long bookId;
        private String authorName;
        private String title;

        // getters and setters

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Long customerId) {
            this.customerId = customerId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public Long getBookId() {
            return bookId;
        }

        public void setBookId(Long bookId) {
            this.bookId = bookId;
        }

        public String getAuthorName() {
            return authorName;
        }

        public void setAuthorName(String authorName) {
            this.authorName = authorName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
    static class CreateBorrowingRequest {
        private Long customerId;
        private Long bookId;

        public Long getCustomerId() {
            return customerId;
        }

        public Long getBookId() {
            return bookId;
        }
    }
}
