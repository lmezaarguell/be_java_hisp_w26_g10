package com.api.socialmeli.service;

import com.api.socialmeli.dto.FollowedByDto;
import com.api.socialmeli.dto.FollowedBySellerDto;
import com.api.socialmeli.entity.FollowedBy;
import com.api.socialmeli.entity.Seller;

import java.util.List;

public interface IFollowedByService {
    void addFollow(int seller_id, int buyer_id);
    List<FollowedByDto> getFollowersOfSeller(int seller_id);
    List<FollowedByDto> getFollowsOfBuyer(int buyer_id);
    void deleteFollow(int seller_id, int buyer_id);
}
