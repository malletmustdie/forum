package ru.job4j.forum.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.job4j.forum.dto.PostDto;
import ru.job4j.forum.dto.UserDto;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
class RegControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

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

    @Test
    @WithMockUser
    void whenSaveNewUserAndRedirectToLoginPage() throws Exception {
        var user = UserDto.builder()
                          .username("user")
                          .password("root")
                          .build();
        mvc.perform(MockMvcRequestBuilders.post("/reg")
                                          .param("username", user.getUsername())
                                          .param("password", user.getPassword()))
           .andDo(MockMvcResultHandlers.print())
           .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
           .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));

        ArgumentCaptor<UserDto> argument = ArgumentCaptor.forClass(UserDto.class);
        Mockito.verify(userService).saveUser(argument.capture());
        Assertions.assertEquals(user.getUsername(), argument.getValue().getUsername());
    }

}