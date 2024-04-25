package com.api.socialmeli.repository;

import com.api.socialmeli.entity.Buyer;

import java.util.List;

public interface IBuyerRepository {
    Buyer save();
    Buyer getById(Integer id);

    List<Buyer> getAll();
    Buyer update(Buyer buyer);
    void delete(int id);
}
