package ru.job4j.forum.service.impl;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.forum.dto.UserDto;
import ru.job4j.forum.mapper.UserMapper;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceMemoryImpl implements UserService {

    private final UserMapper userMapper;

    private final List<User> users = new ArrayList<>();

    @Override
    public User saveUser(UserDto userDto) {
        var user = userMapper.map(userDto);
        users.add(user);
        return user;
    }

}
