package com.api.socialmeli.service.impl;

import com.api.socialmeli.entity.Buyer;
import com.api.socialmeli.entity.Seller;
import com.api.socialmeli.exception.NotFoundException;
import com.api.socialmeli.repository.IBuyerRepository;
import com.api.socialmeli.service.IBuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerServiceImpl implements IBuyerService {
    @Autowired
    IBuyerRepository buyerRepository;
    @Override
    public void unfollowUser(Integer followerId, Integer toUnfollowId) {
        Buyer buyer = buyerRepository.getById(followerId);
        if(buyer == null){
            throw new NotFoundException("El usuario no existe");
        }
        Seller sellerToUnfollow = buyer.getFollowed().
                                             stream().
                                             filter(e->e.getUser_id().equals(toUnfollowId)).
                                             findFirst().
                                             orElse(null);
        if(sellerToUnfollow == null){
            throw new NotFoundException("No sigues al vendedor ");
        }

        buyerRepository.getById(followerId).getFollowed().removeIf(e->e.getUser_id().equals(toUnfollowId));
    }
}
