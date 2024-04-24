package com.api.socialmeli.service.impl;

import com.api.socialmeli.dto.BuyerFollowedListDTO;
import com.api.socialmeli.service.IBuyerService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Override
    public BuyerFollowedListDTO getFollowedListByUser(Integer user_id) {
        ObjectMapper mapper = new ObjectMapper();
        Buyer buyer = buyerRepository.getById(user_id);
        if (buyer!=null){
            return mapper.convertValue(buyer,BuyerFollowedListDTO.class);
        }else {
            throw new NotFoundException("El usuario no existe o no se encuentra registrado.");
        }
    }

    @Override
    public BuyerFollowedListDTO getFollowedListByUserOrderByName(Integer user_id, String order) {
        BuyerFollowedListDTO buyer = getFollowedListByUser(user_id);
        if (buyer.equals(null)){
            throw new NotFoundException("El usuario no existe o no se encuentra registrado.");
        }
        if (order.equals("name_asc")){
            buyer.setFollowed((buyer.getFollowed().stream()
                    .sorted(Comparator.comparing(Seller::getUser_name)).collect(Collectors.toList())));
        }else {
            if (order.equals("name_desc")){
                buyer.setFollowed((buyer.getFollowed().stream()
                        .sorted(Comparator.comparing(Seller::getUser_name).reversed()).collect(Collectors.toList())));
            }else {
                throw new BadRequestException("Parametros incorrectos para el ordenamiento");
            }
        }
        return buyer;
    }
}
