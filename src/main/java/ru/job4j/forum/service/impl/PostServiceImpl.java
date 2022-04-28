package ru.job4j.forum.service.impl;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.forum.dto.PostDto;
import ru.job4j.forum.mapper.PostMapper;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostRepository;
import ru.job4j.forum.service.PostService;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final PostMapper postMapper;

    @Override
    public Post savePost(PostDto dto) {
        return Optional.of(dto)
                       .map(postMapper::map)
                       .map(postRepository::save)
                       .orElseThrow();
    }

    @Override
    public void updatePost(PostDto dto) {
        var post = postMapper.map(dto);
        postRepository.findById(post.getId())
                      .ifPresent(actualPost -> {
                          actualPost.setName(post.getName());
                          actualPost.setDescription(post.getDescription());
                          postRepository.save(actualPost);
                      });
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Optional<PostDto> findPostById(Integer id) {
        return postRepository.findById(id)
                             .map(postMapper::map);

    }

}
