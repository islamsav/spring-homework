package com.example.MyBookShopApp.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookstoreAuthUserTest {


    private final MockMvc mockMvc;

    @Autowired
    public BookstoreAuthUserTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void correctLoginByEmailTest() throws Exception {
        mockMvc.perform(formLogin("/signin").user("test@test.test").password("123456"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void correctLoginByPhoneTest() throws Exception {
        mockMvc.perform(formLogin("/signin").user("+7 (111) 111-11-11").password("123456"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/signin"));
    }
}
