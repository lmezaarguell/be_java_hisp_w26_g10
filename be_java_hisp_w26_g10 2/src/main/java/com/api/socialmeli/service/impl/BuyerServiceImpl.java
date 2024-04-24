package com.api.socialmeli.service.impl;

import com.api.socialmeli.dto.BuyerFollowedListDTO;
import com.api.socialmeli.entity.Buyer;
import com.api.socialmeli.exception.NotFoundException;
import com.api.socialmeli.repository.IBuyerRepository;
import com.api.socialmeli.service.IBuyerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerServiceImpl implements IBuyerService {

    @Autowired
    IBuyerRepository buyerRepository;

    @Override
    public BuyerFollowedListDTO getFolloedListByUser(Integer user_id) {
        ObjectMapper mapper = new ObjectMapper();
        Buyer buyer = buyerRepository.getById(user_id);
        if (buyer!=null){
            return mapper.convertValue(buyer,BuyerFollowedListDTO.class);
        }else {
            throw new NotFoundException("El usuario no existe o no se encuentra registrado.");
        }
    }
}
