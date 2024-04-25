package com.api.socialmeli.service;

import com.api.socialmeli.dto.SellersCountFollowersDto;

public interface ISellerService {

    SellersCountFollowersDto getCountOfSellerFollowers(Integer user_id);

}
