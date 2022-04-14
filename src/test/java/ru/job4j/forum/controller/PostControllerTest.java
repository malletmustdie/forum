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
import ru.job4j.forum.service.PostService;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PostService postService;

    @Test
    @WithMockUser
    void whenRedirectToCreatePage() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/create?id=1"))
           .andDo(MockMvcResultHandlers.print())
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andExpect(MockMvcResultMatchers.view().name("post/create"));
    }

    @Test
    @WithMockUser
    void whenSaveNewPostAndRedirectToIndexPage() throws Exception {
        var expectedPost = PostDto.builder()
                                  .name("Продаю LADA Granta")
                                  .description("Не бита")
                                  .build();
        mvc.perform(MockMvcRequestBuilders.post("/save")
                                          .param("name", expectedPost.getName())
                                          .param("description", expectedPost.getDescription()))
           .andDo(MockMvcResultHandlers.print())
           .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
           .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
        ArgumentCaptor<PostDto> argument = ArgumentCaptor.forClass(PostDto.class);
        Mockito.verify(postService).savePost(argument.capture());
        Assertions.assertEquals(expectedPost.getName(), argument.getValue().getName());
        Assertions.assertEquals(
                expectedPost.getDescription(),
                argument.getValue().getDescription());
    }

    @Test
    @WithMockUser
    void whenUpdatePostAndRedirectToIndexPage() throws Exception {
        var expectedPost = PostDto.builder()
                                  .id(1)
                                  .name("Продаю LADA Granta")
                                  .description("Не бита")
                                  .build();
        mvc.perform(MockMvcRequestBuilders.post("/update")
                                          .param("id", String.valueOf(expectedPost.getId()))
                                          .param("name", expectedPost.getName())
                                          .param("description", expectedPost.getDescription()))
           .andDo(MockMvcResultHandlers.print())
           .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
           .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
        ArgumentCaptor<PostDto> argument = ArgumentCaptor.forClass(PostDto.class);
        Mockito.verify(postService).updatePost(argument.capture());
        Assertions.assertEquals(expectedPost.getName(), argument.getValue().getName());
        Assertions.assertEquals(
                expectedPost.getDescription(),
                argument.getValue().getDescription()
        );
    }

}