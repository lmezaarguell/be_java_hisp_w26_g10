package com.api.socialmeli.repository.impl;

import com.api.socialmeli.dto.UserDto;
import com.api.socialmeli.entity.Buyer;
import com.api.socialmeli.entity.Seller;
import com.api.socialmeli.repository.ISellerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SellerRepositoryImpl implements ISellerRepository {
    List<Seller> sellers = new ArrayList<>();

    public SellerRepositoryImpl() {
        this.sellers = this.loadData();
    }

    @Override
    public void save() {

    }

    @Override
    public Seller getById(int id) {
        return null;
    }

    @Override
    public List<Seller> getAll() {
        return sellers;
    }

    @Override
    public Seller update(Seller seller) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    public List<Seller> loadData(){
        List<Seller> sellers = new ArrayList<>();
        String route = "classpath:seller.json";
        ObjectMapper objectMapper = new ObjectMapper();
        File file;
        try {
            file = ResourceUtils.getFile(route);
            List<Map<String, Object>> data = objectMapper.readValue(file, List.class);

            for (Map<String, Object> register : data) {
                Seller seller = new Seller();
                seller.setUser_id((Integer) register.get("user_id"));
                seller.setUser_name((String) register.get("user_name").toString());
                sellers.add(seller);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sellers;
    }
}
