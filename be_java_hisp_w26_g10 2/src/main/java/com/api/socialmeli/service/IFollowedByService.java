package com.api.socialmeli.service;

import com.api.socialmeli.dto.FollowedBySellerDto;

public interface IFollowedByService {
    void addFollow(int seller_id, int buyer_id);
    FollowedBySellerDto getFollowersOfSeller(int seller_id);
    void deleteFollow(int seller_id, int buyer_id);
}
