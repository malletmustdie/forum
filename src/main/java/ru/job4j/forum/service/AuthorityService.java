package ru.job4j.forum.service;

import ru.job4j.forum.model.Authority;

public interface AuthorityService {

    Authority findByAuthority(String authority);

}
