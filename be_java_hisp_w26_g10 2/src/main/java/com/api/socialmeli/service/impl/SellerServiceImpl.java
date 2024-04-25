package com.api.socialmeli.service.impl;

import com.api.socialmeli.dto.SellersCountFollowersDto;
import com.api.socialmeli.entity.Seller;
import com.api.socialmeli.repository.ISellerRepository;
import com.api.socialmeli.service.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements ISellerService {


    @Autowired
    ISellerRepository iSellerRepository;

    @Override
    public SellersCountFollowersDto getCountOfSellerFollowers(int user_id) {
        List<Seller> sellerList = iSellerRepository.getAll();


        return null;
    }
}
