package com.example.demo.service;

import com.example.demo.persistence.BookEntity;
import com.example.demo.persistence.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public BookDTO getBook(Long id){
        Optional<BookEntity> optEntity = bookRepository.findById(id);
        if(!optEntity.isPresent()) {
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
        entity.setAuthor(dto.getName());
        bookRepository.save(entity);
        return entity.getId();
    }
}
