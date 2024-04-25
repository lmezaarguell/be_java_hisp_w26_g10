package com.api.socialmeli.repository.impl;

import com.api.socialmeli.entity.Post;
import com.api.socialmeli.repository.IPostRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepositoryImpl implements IPostRepository {
    private List<Post> listPosts = new ArrayList<>();

    public PostRepositoryImpl() throws IOException {
        this.loadPosts();
    }

    @Override
    public List<Post> loadAllPosts() {
        return listPosts;
    }

    public void loadPosts() throws IOException {
        File file;
        ObjectMapper objectMapper = new ObjectMapper();
        List<Post> posts;

        file = ResourceUtils.getFile("classpath:post.json");
        posts = objectMapper.readValue(file, new TypeReference<List<Post>>() {
        });

        listPosts = posts;
    }

    @Override
    public int searchPostId(){

        int max = this.listPosts.stream().mapToInt(Post::getPost_id).max().orElse(0);
        return max + 1;
    }
}
