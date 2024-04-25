package com.api.socialmeli.service.impl;

import com.api.socialmeli.dto.PostDto;
import com.api.socialmeli.entity.Post;
import com.api.socialmeli.exception.BadRequestException;
import com.api.socialmeli.repository.IPostRepository;
import com.api.socialmeli.service.IPostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements IPostService {
    @Autowired
    IPostRepository postRepository;
    private final List<Post> posts = new ArrayList<>();
    int postId;

    @Override
    public PostDto publishPost(PostDto postDto) {
        postId = getPostId();
        postDto.setPost_id(postId);
        Post post = convertToPost(postDto);
        posts.add(post);
        return postDto;
    }

    private int generatePostId(){
        return this.postRepository.searchPostId();
    }

    private Post convertToPost(PostDto postDto) {
        try {
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
            return mapper.convertValue(postDto, Post.class);
        } catch (Exception e) {
            throw new BadRequestException(e + "Reviewed data");
        }
    }

    private int getPostId() {
        if (posts.isEmpty()){
            postId = generatePostId();
        }else {
            postId = (posts.stream().mapToInt(Post::getPost_id).max().orElse(0) +1);
        }
        return postId;
    }
}
