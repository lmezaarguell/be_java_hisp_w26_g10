package com.api.socialmeli.service;

import java.util.List;

import com.api.socialmeli.entity.Buyer;

public interface IBuyerService {
    public List<Buyer> getAll();
    public Buyer followUser(Integer userId, Integer userIdToFollow);
    Buyer getBuyerById(Integer id);
}
