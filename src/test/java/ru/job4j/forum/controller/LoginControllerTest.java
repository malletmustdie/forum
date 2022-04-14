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
class LoginControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser
    void whenRedirectToLoginPage() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/login"))
           .andDo(MockMvcResultHandlers.print())
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andExpect(MockMvcResultMatchers.view().name("login"));
    }

    @Test
    @WithMockUser
    void whenRedirectToLoginPageWithErrorMsg() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/login?error=true"))
           .andDo(MockMvcResultHandlers.print())
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andExpect(MockMvcResultMatchers.view().name("login"))
           .andExpect(MockMvcResultMatchers.model().attribute(
                   "errorMessage",
                   "Username or Password is incorrect !!"));
    }

    @Test
    void whenRedirectToLoginPageLogoutMsg() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/login?logout=true"))
           .andDo(MockMvcResultHandlers.print())
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andExpect(MockMvcResultMatchers.view().name("login"))
           .andExpect(MockMvcResultMatchers.model().attribute(
                   "errorMessage",
                   "You have been successfully logged out !!"));
    }

}