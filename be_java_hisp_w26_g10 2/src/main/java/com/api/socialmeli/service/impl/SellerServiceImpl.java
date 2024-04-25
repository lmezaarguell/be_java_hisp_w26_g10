package com.api.socialmeli.service.impl;

import com.api.socialmeli.dto.FollowedBySellerDto;
import com.api.socialmeli.dto.SellersCountFollowersDto;
import com.api.socialmeli.dto.UserDto;
import com.api.socialmeli.entity.Buyer;
import com.api.socialmeli.entity.Seller;
import com.api.socialmeli.exception.NotFoundException;
import com.api.socialmeli.repository.IBuyerRepository;
import com.api.socialmeli.repository.ISellerRepository;
import com.api.socialmeli.service.ISellerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SellerServiceImpl implements ISellerService {


    @Autowired
    ISellerRepository iSellerRepository;
    @Autowired
    IBuyerRepository iBuyerRepository;

    @Override
    public SellersCountFollowersDto getCountOfSellerFollowers(int user_id) {
        List<Seller> sellerList = iSellerRepository.getAll();


        return null;
    }

    @Override
    public FollowedBySellerDto getFollowersOfSeller(int seller_id) {
        /* se comprueba que el vendedor exista */
        Seller seller = iSellerRepository.getById(seller_id);
        if(seller == null){
            throw new NotFoundException("No se encontro al venedor con el id: " + Integer.toString(seller_id));
        }

        /* se optienen todos los compradores */
        List<Buyer> buyers = iBuyerRepository.getAll();
        if(buyers.isEmpty()){
            throw new NotFoundException("No hay compradores registrados");
        }

        /* se busca si el comprador sigue al vendedor y se agrega el comprador a
        * lista de seguidos */
        List<Buyer> buyersFollowers = new ArrayList<>();
        for(Buyer buyer: buyers){
            for(Seller sellerFollowed: buyer.getFollowed()){
                if(sellerFollowed.getUser_id().equals(seller_id)){
                    buyersFollowers.add(buyer);
                }
            }
        }

        /* se comprueba que el vendedor tenga seguidores */
        if(buyersFollowers.isEmpty()){
            throw new NotFoundException("No se encontraron seguidores de este comprador: " + Integer.toString(seller_id));
        }


        /* se crea su dto de respuesta */
        ObjectMapper objectMapper = new ObjectMapper();
        List<UserDto> followersDto = buyersFollowers.stream()
                .map(followedBy -> objectMapper.convertValue(followedBy, UserDto.class))
                .collect(Collectors.toList());

        return new FollowedBySellerDto(
                seller.getUser_id(),
                seller.getUser_name(),
                followersDto
        );
    }
}
