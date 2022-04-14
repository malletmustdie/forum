package ru.job4j.forum.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class RegControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser
    void whenRedirectToLoginPage() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/reg"))
           .andDo(MockMvcResultHandlers.print())
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andExpect(MockMvcResultMatchers.view().name("reg"));
    }

    @Test
    @WithMockUser
    void whenRedirectToLoginPageWithError() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/reg?error=true"))
           .andDo(MockMvcResultHandlers.print())
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andExpect(MockMvcResultMatchers.view().name("reg"))
           .andExpect(MockMvcResultMatchers.model().attribute(
                   "errorMessage",
                   "Username is already exists!"));
    }

}