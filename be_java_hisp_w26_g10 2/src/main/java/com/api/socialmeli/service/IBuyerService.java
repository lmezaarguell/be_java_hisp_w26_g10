package com.api.socialmeli.service;

import com.api.socialmeli.entity.Buyer;

public interface IBuyerService {
    public Buyer followUser(Integer userId, Integer userIdToFollow);
}
