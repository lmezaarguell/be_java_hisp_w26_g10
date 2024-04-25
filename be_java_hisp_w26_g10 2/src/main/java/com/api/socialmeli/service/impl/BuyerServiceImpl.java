package com.api.socialmeli.service.impl;

import com.api.socialmeli.entity.Buyer;
import com.api.socialmeli.entity.Seller;
import com.api.socialmeli.repository.IBuyerRepository;
import com.api.socialmeli.repository.ISellerRepository;
import com.api.socialmeli.exception.NotFoundException;
import com.api.socialmeli.service.IBuyerService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerServiceImpl implements IBuyerService {

    @Autowired
    IBuyerRepository buyerRepository;
    @Autowired
    ISellerRepository sellerRepository;

    @Override
    public List<Buyer> getAll() {
        return buyerRepository.getAll();
    }

    @Override
    public Buyer followUser(Integer userId, Integer userIdToFollow) {
        Seller userFollowed = sellerRepository.getById(userIdToFollow);
        return buyerRepository.followUser(userId, userFollowed);
    }
    /*
    US 0007: Poder realizar la acción de “Unfollow” (dejar de seguir) a un determinado vendedor.
     */
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

