package com.api.socialmeli.repository;

import com.api.socialmeli.entity.Buyer;
import com.api.socialmeli.entity.Seller;

import java.util.List;

public interface IBuyerRepository {
    Buyer save();
    Buyer getById(int id);
    List<Buyer> getAll();
    Buyer update(Buyer buyer);
    void delete(int id);
    
    Buyer followUser(Integer userId, Seller userFollowed);
}
