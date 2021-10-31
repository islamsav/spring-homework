package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.service.book.BooksRatingAndPopularityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PopularControllerTest {

    @MockBean
    private BooksRatingAndPopularityService booksRatingAndPopularityService;

    private final MockMvc mockMvc;

    @Autowired
    public PopularControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }


    @Test
    void testGetNextSearchPage() throws Exception {
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders
                .get("/books/popular")
                .param("offset", "0")
                .param("limit", "6")
                .header("Content-Type", "application/json");

        mockMvc.perform(paramResult)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").isNumber())
                .andExpect(jsonPath("$.books").exists())
                .andExpect(jsonPath("$.books").isArray());
    }

    @Test
    void testPageType() {
        when(this.booksRatingAndPopularityService.getPageOfPopularBooks(any(), any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
    }
}
