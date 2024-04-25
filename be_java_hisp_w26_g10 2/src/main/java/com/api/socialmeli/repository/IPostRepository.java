package com.api.socialmeli.repository;

import com.api.socialmeli.entity.Post;

import java.util.List;
import java.util.Optional;

public interface IPostRepository {
    List<Post> loadAllPosts();
    int searchPostId();

}
