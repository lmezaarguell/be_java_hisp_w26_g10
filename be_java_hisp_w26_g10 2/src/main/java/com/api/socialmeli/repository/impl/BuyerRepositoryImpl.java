package com.api.socialmeli.repository.impl;

import com.api.socialmeli.entity.Buyer;
import com.api.socialmeli.entity.Seller;
import com.api.socialmeli.repository.IBuyerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class BuyerRepositoryImpl implements IBuyerRepository {
    List<Buyer> buyers = new ArrayList<>();

    public BuyerRepositoryImpl(){
        this.buyers = this.loadData();
    }

    @Override
    public Buyer save() {
        return null;
    }

    @Override
    public Buyer getById(int id) {
        return buyers.stream().filter(
                b -> b.getUser_id().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Buyer> getAll() {
        return buyers;
    }

    @Override
    public Buyer update(Buyer buyer) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Buyer followUser(Integer userId, Seller userFollowed) {
        Buyer userFollowing = getById(userId);
        userFollowing.getFollowed().add(userFollowed);
        return userFollowing;
    }

    public List<Buyer> loadData(){
        List<Buyer> buyers = new ArrayList<>();
        String route = "classpath:buyer.json";
        ObjectMapper objectMapper = new ObjectMapper();
        File file;
        try {
            file = ResourceUtils.getFile(route);
            List<Map<String, Object>> data = objectMapper.readValue(file, List.class);

            for (Map<String, Object> register : data) {
                Buyer buyer = new Buyer();
                buyer.setUser_id((Integer) register.get("user_id"));
                buyer.setUser_name((String) register.get("user_name").toString());
                buyer.setFollowed((List<Seller>) register.get("followed"));
                buyers.add(buyer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return buyers;
    }
}