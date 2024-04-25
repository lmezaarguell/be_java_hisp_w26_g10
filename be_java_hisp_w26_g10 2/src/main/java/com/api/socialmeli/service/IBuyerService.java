package com.api.socialmeli.service;

import com.api.socialmeli.dto.BuyerFollowedListDTO;

import com.api.socialmeli.entity.Buyer;

public interface IBuyerService {

    //Se define el método en la interface del servicio del cliente para su debida implementación
    public BuyerFollowedListDTO getFollowedListByUser(Integer user_id, String order);
    public Buyer followUser(Integer userId, Integer userIdToFollow);
}
