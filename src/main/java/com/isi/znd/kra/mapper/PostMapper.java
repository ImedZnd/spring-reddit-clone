package com.isi.znd.kra.mapper;

import com.isi.znd.kra.dto.PostRequest;
import com.isi.znd.kra.dto.PostResponse;
import com.isi.znd.kra.model.Post;
import com.isi.znd.kra.model.Subreddit;
import com.isi.znd.kra.model.User;
import com.isi.znd.kra.repository.CommentRepository;
import com.isi.znd.kra.repository.VoteRepository;
import com.isi.znd.kra.service.AuthService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subreddit", source = "subreddit")
    @Mapping(target = "user", source = "user")
    Post map(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "userName", source = "user.username")
    PostResponse mapToDto(Post post);

}