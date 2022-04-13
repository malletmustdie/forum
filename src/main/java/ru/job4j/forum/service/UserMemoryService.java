package ru.job4j.forum.service;

import ru.job4j.forum.dto.UserDto;
import ru.job4j.forum.model.User;

public interface UserMemoryService {

    User saveUser(UserDto user);

}
