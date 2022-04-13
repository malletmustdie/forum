package ru.job4j.forum.repository;

import java.util.List;

import ru.job4j.forum.model.Post;

public interface PostMemoryRepository {

    List<Post> findAll();

    Post getById(Integer id);

    void save(Post post);

    void update(Post post);

}
