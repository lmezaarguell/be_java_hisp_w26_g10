package com.api.socialmeli.repository;

import com.api.socialmeli.entity.Seller;

import java.util.List;

public interface ISellerRepository {
    void save();
    Seller getById(int id);
    List<Seller> getAll();
    Seller update(Seller seller);
    void delete(int id);
}
