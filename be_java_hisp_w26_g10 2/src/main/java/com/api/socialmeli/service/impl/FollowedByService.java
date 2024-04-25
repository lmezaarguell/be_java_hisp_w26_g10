package com.api.socialmeli.service.impl;

import com.api.socialmeli.dto.FollowedBySellerDto;
import com.api.socialmeli.service.IFollowedByService;
import org.springframework.beans.factory.annotation.Autowired;

public class FollowedByService implements IFollowedByService {

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
