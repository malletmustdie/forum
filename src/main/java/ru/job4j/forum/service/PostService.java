package ru.job4j.forum.service;

import java.util.List;
import java.util.Optional;

import ru.job4j.forum.dto.PostDto;
import ru.job4j.forum.model.Post;

public interface PostService {

    Post savePost(PostDto dto);

    void updatePost(PostDto dto);

    List<Post> getAllPosts();

    Optional<PostDto> findPostById(Integer id);

}
