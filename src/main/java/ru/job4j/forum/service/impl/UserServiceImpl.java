package ru.job4j.forum.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.forum.dto.UserDto;
import ru.job4j.forum.mapper.UserMapper;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.UserRepository;
import ru.job4j.forum.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public User saveUser(UserDto userDto) {
        var user = userMapper.map(userDto);
        return userRepository.save(user);
    }

}
