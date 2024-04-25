package com.api.socialmeli.service.impl;

import com.api.socialmeli.dto.FollowedBySellerDto;
import com.api.socialmeli.entity.FollowedBy;
import com.api.socialmeli.exception.BadRequestException;
import com.api.socialmeli.repository.IFollowedByRepository;
import com.api.socialmeli.service.IFollowedByService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FollowedByService implements IFollowedByService {

    @Autowired
    IFollowedByRepository iFollowedByRepository;

    @Override
    public void addFollow(int seller_id, int buyer_id) {
        /* Se comprueba si el follow ya existe */
        Optional<FollowedBy> existsFollow = iFollowedByRepository.getFollowById(seller_id, buyer_id);
        if(!existsFollow.isPresent()){
            throw new BadRequestException("El follow ya existe");
        }

        /* se guarda el save */
        FollowedBy followedBy = new FollowedBy(seller_id, buyer_id);
        iFollowedByRepository.save(followedBy);
    }

    @Override
    public FollowedBySellerDto getFollowersOfSeller(int seller_id) {
        /*  */
        return null;
    }

    @Override
    public void deleteFollow(int seller_id, int buyer_id) {

    }
}
