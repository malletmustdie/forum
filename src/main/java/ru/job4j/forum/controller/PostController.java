package ru.job4j.forum.controller;

import java.time.LocalDate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        var postDto = postService.findPostById(id);
        model.addAttribute("post", postDto);
        return "post/update";
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
