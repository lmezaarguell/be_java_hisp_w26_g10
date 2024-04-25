package com.api.socialmeli.service.impl;

import com.api.socialmeli.entity.Buyer;
import com.api.socialmeli.entity.Seller;
import com.api.socialmeli.exception.BadRequestException;
import com.api.socialmeli.repository.IBuyerRepository;
import com.api.socialmeli.repository.ISellerRepository;
import com.api.socialmeli.service.IBuyerService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerServiceImpl implements IBuyerService {

    @Autowired
    private IBuyerRepository buyerRepository;

    @Autowired
    private ISellerRepository sellerRepository;

    @Override
    public List<Buyer> getAll() {
        return buyerRepository.getAll();
    }

    @Override
    public Buyer followUser(Integer userId, Integer userIdToFollow) {
        Seller userFollowed = sellerRepository.getById(userIdToFollow);
        Buyer userFollowing = buyerRepository.getById(userId);
        if(userFollowed == null || userFollowing == null){
            throw new BadRequestException("Seller or Customer not found");
        }
        if(userFollowing.getFollowed().contains(userFollowed)){
            throw new BadRequestException("User is already following seller");
        }
        return buyerRepository.followUser(userFollowing, userFollowed);
    }

    @Override
    public Buyer getBuyerById(Integer id) {
        return buyerRepository.getById(id);
    }
}
