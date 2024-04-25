package com.api.socialmeli.service;

import com.api.socialmeli.dto.PostsByFollowedDto;

public interface IPostService {
    PostsByFollowedDto getPostsByFollowed(Integer userId, String order);

}
