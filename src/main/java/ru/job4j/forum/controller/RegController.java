package ru.job4j.forum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.dto.UserDto;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.service.UserService;

@Slf4j
@RequiredArgsConstructor
@Controller
public class RegController {

    private final PasswordEncoder encoder;

    private final UserService userService;

    @PostMapping("/reg")
    public String regSave(@ModelAttribute UserDto userDto) {
        userDto.setEnabled(true);
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        userDto.setAuthority(Authority.builder()
                                      .authority("ROLE_USER")
                                      .build());
        userService.saveUser(userDto);
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String regPage(@RequestParam(value = "error", required = false) String error,
                          Model model) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "Username is already exists!";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "reg";
    }

    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(Exception e) {
        log.error(e.getMessage());
        return "redirect:/reg?error=true";
    }

}
