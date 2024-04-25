package com.api.socialmeli.service.impl;

import com.api.socialmeli.dto.FollowedByDto;
import com.api.socialmeli.dto.FollowedBySellerDto;
import com.api.socialmeli.dto.UserDto;
import com.api.socialmeli.entity.FollowedBy;
import com.api.socialmeli.entity.Seller;
import com.api.socialmeli.exception.BadRequestException;
import com.api.socialmeli.exception.NotFoundException;
import com.api.socialmeli.repository.IFollowedByRepository;
import com.api.socialmeli.service.IBuyerService;
import com.api.socialmeli.service.IFollowedByService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FollowedByService implements IFollowedByService {
    final static ObjectMapper objectMapper = new ObjectMapper();


    @Autowired
    IFollowedByRepository iFollowedByRepository;
    IBuyerService iBuyerService;

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
    public List<FollowedByDto> getFollowersOfSeller(Seller seller) {
        /* se extrae la lista de seguidores del vendedor */
        List<FollowedBy> followers = iFollowedByRepository.getFollowedByList(seller.getUser_id());

        /* se comprueba si existen seguidores */
        if(followers.isEmpty()){
            throw new NotFoundException("El Vendedor no tiene seguidores");
        }

        // Mapear la lista de FollowedBy a FollowedByDto
        List<FollowedByDto> followedByDtoList = followers.stream()
                .map(followedBy -> objectMapper.convertValue(followedBy, FollowedByDto.class))
                .collect(Collectors.toList());

        /* se retorna la respuesta */
        return followedByDtoList;
    }

    @Override
    public void deleteFollow(int seller_id, int buyer_id) {
    }
}
