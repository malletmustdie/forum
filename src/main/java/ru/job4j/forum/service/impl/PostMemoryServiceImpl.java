package ru.job4j.forum.service.impl;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.forum.dto.PostDto;
import ru.job4j.forum.mapper.PostMapper;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostMemoryRepository;
import ru.job4j.forum.service.PostService;

@Service
@RequiredArgsConstructor
public class PostMemoryServiceImpl implements PostService {

    private final PostMemoryRepository memoryRepository;

    private final PostMapper postMapper;

    @Override
    public Post savePost(PostDto dto) {
        var post = postMapper.map(dto);
        memoryRepository.save(post);
        return post;
    }

    @Override
    public void updatePost(PostDto dto) {
        var post = postMapper.map(dto);
        memoryRepository.update(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return memoryRepository.findAll();
    }

    @Override
    public PostDto findPostById(Integer id) {
        return postMapper.map(memoryRepository.getById(id));
    }

}
