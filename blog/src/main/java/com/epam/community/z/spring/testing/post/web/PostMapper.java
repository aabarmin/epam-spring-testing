package com.epam.community.z.spring.testing.post.web;

import com.epam.community.z.spring.testing.post.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {
  PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

  PostModel toModel(Post post);
}
