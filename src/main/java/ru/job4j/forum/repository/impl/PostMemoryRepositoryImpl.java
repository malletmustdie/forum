package ru.job4j.forum.repository.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostMemoryRepository;

@Repository
public class PostMemoryRepositoryImpl implements PostMemoryRepository {

    private final Map<Integer, Post> posts = new HashMap<>();

    public PostMemoryRepositoryImpl() {
        posts.put(1,
                Post.builder()
                    .id(1)
                    .name("Продаю Lada Granta")
                    .description("Один владелец."
                                         .concat("\n")
                                         .concat("2018 год выпуска.")
                                         .concat("\n")
                                         .concat("Покупалась в салоне.")
                                         .concat("\n")
                                         .concat("В ДТП не участвовала."))
                    .created(LocalDate.now())
                    .build()
        );
    }

    @Override
    public void save(Post post) {
        posts.put(post.getId(), post);
    }

    @Override
    public void update(Post post) {
        posts.replace(post.getId(), post);
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(posts.values());
    }

    @Override
    public Post getById(Integer id) {
        return posts.get(id);
    }

}
