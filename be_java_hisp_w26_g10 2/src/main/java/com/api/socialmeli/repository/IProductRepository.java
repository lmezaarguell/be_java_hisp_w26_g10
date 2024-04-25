package com.api.socialmeli.repository;

import com.api.socialmeli.entity.Product;

public interface IProductRepository {
    Product save();
    Product getById(int id);
    Product update(Product product);
    void delete(int id);
}
