package com.api.socialmeli.service.impl;

import com.api.socialmeli.entity.Buyer;
import com.api.socialmeli.entity.Seller;
import com.api.socialmeli.repository.IBuyerRepository;
import com.api.socialmeli.repository.ISellerRepository;
import com.api.socialmeli.dto.BuyerFollowedListDTO;
import com.api.socialmeli.exception.BadRequestException;
import com.api.socialmeli.exception.NotFoundException;
import com.api.socialmeli.service.IBuyerService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
@Service
public class BuyerServiceImpl implements IBuyerService {

    @Autowired
    private IBuyerRepository buyerRepository;

    @Autowired
    private ISellerRepository sellerRepository;

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
    public Buyer getBuyerById(Integer id) {
        return buyerRepository.getById(id);
    }

    //Servicio que implementa la logica para obtener la lista de todos los vendedores que sigue
    //un determinado usuario con la opcion de poder ordenarlo por nombre ascendente o descentente
    @Override
    public BuyerFollowedListDTO getFollowedListByUser(Integer user_id, String order) {
        ObjectMapper mapper = new ObjectMapper();
        Buyer buyer = buyerRepository.getById(user_id);//Se obtiene el usuario solicitado
        if (buyer!=null){//Valida que sea un usario registrado
            if (order!=null){//revisa si se solicito un ordenamiento desde el controlador
                if (order.equals("name_asc")){//Ordenamiento ascendente mediante expresiones lambda
                    buyer.setFollowed((buyer.getFollowed().stream()
                            .sorted(Comparator.comparing(Seller::getUser_name)).toList()));
                }else {
                    if (order.equals("name_desc")){//Ordenamiento descendente mediante expresiones lambda
                        buyer.setFollowed((buyer.getFollowed().stream()
                                .sorted(Comparator.comparing(Seller::getUser_name).reversed()).toList()));
                    }else {
                        throw new BadRequestException("Parametros incorrectos para el ordenamiento");
                    }
                }
            }
            //Retorna la salida solicitada o en su caso las respectivas excepciones
            return mapper.convertValue(buyer,BuyerFollowedListDTO.class);
        }else {
            throw new NotFoundException("El usuario no existe o no se encuentra registrado.");
        }
    }
}
