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
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@WebMvcTest(BookController.class)
@AutoConfigureRestDocs(outputDir = "/target/generated-snippets")
public class DemoTest {
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
}
