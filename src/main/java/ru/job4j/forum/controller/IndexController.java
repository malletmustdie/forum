package ru.job4j.forum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.forum.service.PostService;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostService postService;

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute(
                "user",
                SecurityContextHolder.getContext()
                                     .getAuthentication()
                                     .getPrincipal()
        );
        model.addAttribute("posts", postService.getAllPosts());
        return "index";
    }

}
