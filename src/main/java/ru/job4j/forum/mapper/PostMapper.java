package ru.job4j.forum.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.job4j.forum.config.SpringMapperConfig;
import ru.job4j.forum.dto.PostDto;
import ru.job4j.forum.model.Post;

@Mapper(config = SpringMapperConfig.class)
public interface PostMapper {

    @Mapping(source = "dto.id", target = "id")
    @Mapping(source = "dto.name", target = "name")
    @Mapping(source = "dto.description", target = "description")
    @Mapping(source = "dto.created", target = "created")
    Post map(PostDto dto);

    @Mapping(source = "entity.id", target = "id")
    @Mapping(source = "entity.name", target = "name")
    @Mapping(source = "entity.description", target = "description")
    @Mapping(source = "entity.created", target = "created")
    PostDto map(Post entity);

}
