package com.api.socialmeli.repository.impl;

import com.api.socialmeli.entity.Post;
import com.api.socialmeli.repository.IPostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Repository;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class PostRepositoryImpl implements IPostRepository {
    private List<Post> post;

    public PostRepositoryImpl(){
        post = loadData();
    }

    @Override
    public Post save() {
        return null;
    }

    @Override
    public Post getById(int id) {
        return null;
    }

    @Override
    public List<Post> getAll() {
        return post;
    }

    @Override
    public Post update(Post post) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    public List<Post> loadData(){
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());;
        try {
            File file = new File(JsonMapper.class.getClassLoader().getResource("posts.json").getFile());
            return Arrays.asList(objectMapper.readValue(file, Post[].class));
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}