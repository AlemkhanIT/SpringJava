package com.example.demo;

import com.example.demo.controller.Book;
import com.example.demo.controller.BookController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@WebMvcTest(BookController.class)
@AutoConfigureRestDocs(outputDir = "/target/generated-snippets")
public class bookTest {
    @Autowired
    private ObjectMapper mapper;
    private MockMvc mockMvc;
    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }
    @Test
    public void testVytvorKnihu() throws Exception{
        Book b = new Book("lll","lll","lll", "asd",5);

        mockMvc.perform(
                        post("/api/books")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(b))
                )
                .andExpect(status().isOk())
                .andDo(document("{methodName}",
                        requestFields(fieldWithPath("authorFirstName").description("autor meno"),
                                fieldWithPath("title").description("nazov"),
                                fieldWithPath("id").description("id"),
                                fieldWithPath("count").description("count"),
                                fieldWithPath("id").description("id"),
                                fieldWithPath("isbn").description("isbn"),
                                fieldWithPath("authorLastName").description("autor priezvisko")

                        )))
                .andReturn();
    }
    @Test
    public void testListBooks() throws Exception {
        final List<Book> books = new ArrayList<>();
        books.add(new Book("Title1", "Author1", "1234567890", "First", 1));
        books.add(new Book("Title2", "Author2", "0987654321", "Second", 2));

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andDo(document("{methodName}",
                        responseFields(
                                fieldWithPath("[].authorFirstName").description("Author's first name"),
                                fieldWithPath("[].authorLastName").description("Author's last name"),
                                fieldWithPath("[].title").description("Title of the book"),
                                fieldWithPath("[].id").description("ID of the book"),
                                fieldWithPath("[].isbn").description("ISBN of the book"),
                                fieldWithPath("[].count").description("Count of the book")
                        )
                ))
                .andReturn();
    }

    @Test
    public void testReadBook() throws Exception {
        Long bookId = 1L; // Assuming you have a book with ID 1
        mockMvc.perform(get("/api/books/{bookId}", bookId))
                .andExpect(status().isOk())
                .andDo(document("{methodName}",
                        responseFields(
                                fieldWithPath("authorFirstName").description("Author's first name"),
                                fieldWithPath("authorLastName").description("Author's last name"),
                                fieldWithPath("title").description("Title of the book"),
                                fieldWithPath("id").description("ID of the book"),
                                fieldWithPath("isbn").description("ISBN of the book"),
                                fieldWithPath("count").description("Count of the book")
                        )
                ))
                .andReturn();
    }

    @Test
    public void testUpdateBook() throws Exception {
        Long bookId = 1L; // Assuming you have a book with ID 1
        Book updatedBook = new Book("UpdatedTitle", "UpdatedAuthor", "0987654321", "Updated", 3);
        mockMvc.perform(put("/api/books/{bookId}", bookId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(updatedBook)))
                .andExpect(status().isOk())
                .andDo(document("{methodName}",
                        requestFields(
                                fieldWithPath("authorFirstName").description("Author's first name"),
                                fieldWithPath("authorLastName").description("Author's last name"),
                                fieldWithPath("title").description("Title of the book"),
                                fieldWithPath("id").description("ID of the book"),
                                fieldWithPath("isbn").description("ISBN of the book"),
                                fieldWithPath("count").description("Count of the book")
                        )
                ))
                .andReturn();
    }

    @Test
    public void testDeleteBook() throws Exception {
        Long bookId = 1L; // Assuming you have a book with ID 1
        mockMvc.perform(delete("/api/books/{bookId}", bookId))
                .andExpect(status().isOk())
                .andDo(document("{methodName}"))
                .andReturn();
    }



}
