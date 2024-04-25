package com.api.socialmeli.repository.impl;

import com.api.socialmeli.entity.Post;
import com.api.socialmeli.entity.Seller;
import com.api.socialmeli.repository.IPostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepositoryImpl implements IPostRepository {
    private List<Post> post = new ArrayList<>();

    public PostRepositoryImpl(){
        this.post = this.loadData();
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
        List<Post> posts = new ArrayList<>();
        String route = "classpath:post.json";
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        File file;
        try {
            file = ResourceUtils.getFile(route);
            Post[] data = objectMapper.readValue(file, Post[].class);

            for (Post p : data) {
                posts.add(p);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return posts;
    }

    // MCaldera - Funcion de busqueda de valor maximo ´post_id´en json, para generar consecutivo
    @Override
    public int searchPostId(){
        int max = this.post.stream().mapToInt(Post::getPost_id).max().orElse(0);
        return max + 1;
    }
}