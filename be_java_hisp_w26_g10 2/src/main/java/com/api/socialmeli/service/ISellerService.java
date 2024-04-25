package com.api.socialmeli.service;

import com.api.socialmeli.dto.FollowedBySellerDto;
import com.api.socialmeli.dto.SellersCountFollowersDto;

public interface ISellerService {

    SellersCountFollowersDto getCountOfSellerFollowers(int user_id);
    FollowedBySellerDto getFollowersOfSeller(int seller_id); 

}
