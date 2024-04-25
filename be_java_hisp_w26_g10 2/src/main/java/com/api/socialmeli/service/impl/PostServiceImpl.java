package com.api.socialmeli.service.impl;

import com.api.socialmeli.dto.PostsByFollowedDto;
import com.api.socialmeli.service.IPostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements IPostService {
    @Override
    public PostsByFollowedDto getPostsByFollowed(Integer userId) {
       return null;
    }
}
