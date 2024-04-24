package com.api.socialmeli.service;

import com.api.socialmeli.dto.BuyerFollowedListDTO;

public interface IBuyerService {
    public BuyerFollowedListDTO getFolloedListByUser(Integer user_id);
}
