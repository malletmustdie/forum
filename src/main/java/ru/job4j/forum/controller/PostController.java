package ru.job4j.forum.controller;

import java.time.LocalDate;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.forum.dto.PostDto;
import ru.job4j.forum.service.PostService;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/create")
    public String create() {
        return "post/create";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") Integer id, Model model) {
        return postService.findPostById(id)
                          .map(post -> {
                              model.addAttribute("post", post);
                              return "post/update";
                          }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public String save(@ModelAttribute PostDto post) {
        post.setCreated(LocalDate.now());
        postService.savePost(post);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute PostDto post) {
        postService.updatePost(post);
        return "redirect:/";
    }

}
