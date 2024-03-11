package com.example.demo.service;

import com.example.demo.persistence.BookEntity;
import com.example.demo.persistence.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookDTO getBook(Long id){
        Optional<BookEntity> optEntity = bookRepository.findById(id);
        if(optEntity.isEmpty()) {
            return null;
        }
        BookEntity entity = optEntity.get();
        BookDTO dto = new BookDTO();
        dto.setId(entity.getId());
        dto.setAuthor(entity.getAuthor());
        dto.setName(entity.getName());
        return dto;
    }
    public Long createBook(BookDTO dto){
        BookEntity entity = new BookEntity();
        entity.setName(dto.getName());
        entity.setAuthor(dto.getAuthor());
        bookRepository.save(entity);
        return entity.getId();
    }

    public List<BookDTO> listBooks(String title) {
        List<BookEntity> books;
        if (title != null) {
            books = bookRepository.findByTitleIgnoreCase(title);
        } else {
            books = (List<BookEntity>) bookRepository.findAll();
        }
        return books.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<BookDTO> listAllBooks() {
        List<BookEntity> books = (List<BookEntity>) bookRepository.findAll();
        return books.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BookDTO updateBook(Long bookId, BookDTO updatedBook) {
        BookEntity existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        existingBook.setName(updatedBook.getName());
        existingBook.setAuthor(updatedBook.getAuthor());
        BookEntity savedBook = bookRepository.save(existingBook);
        return convertToDTO(savedBook);
    }

    private BookDTO convertToDTO(BookEntity bookEntity) {
        BookDTO dto = new BookDTO();
        dto.setId(bookEntity.getId());
        dto.setName(bookEntity.getName());
        dto.setAuthor(bookEntity.getAuthor());
        return dto;
    }
    public void deleteBook(Long bookId) {
        Optional<BookEntity> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isPresent()) {
            bookRepository.delete(bookOptional.get());
        } else {
            throw new IllegalArgumentException("Book with ID " + bookId + " not found");
        }
    }
}
