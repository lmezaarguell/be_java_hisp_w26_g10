package com.api.socialmeli.service;

import com.api.socialmeli.dto.BuyerFollowedListDTO;

public interface IBuyerService {
    public List<Buyer> getAll();
    //Se define el método en la interface del servicio del cliente para su debida implementación
    public BuyerFollowedListDTO getFollowedListByUser(Integer user_id, String order);
    public Buyer followUser(Integer userId, Integer userIdToFollow);
}
