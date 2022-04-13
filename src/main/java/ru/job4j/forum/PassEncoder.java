package ru.job4j.forum;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PassEncoder {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode("qwerty");
        System.out.println(password);
    }

}
