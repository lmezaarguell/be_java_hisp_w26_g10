package com.api.socialmeli.service.impl;

import com.api.socialmeli.dto.FollowedBySellerDto;
import com.api.socialmeli.repository.IFollowedByRepository;
import com.api.socialmeli.service.IFollowedByService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowedByService implements IFollowedByService {
    @Autowired
    IFollowedByRepository iFollowedByRepository;

    @Override
    public void addFollow(int seller_id, int buyer_id) {

    }

    @Override
    public FollowedBySellerDto getFollowersOfSeller(int seller_id) {
        return null;
    }

    @Override
    public void deleteFollow(int seller_id, int buyer_id) {

    }
}
