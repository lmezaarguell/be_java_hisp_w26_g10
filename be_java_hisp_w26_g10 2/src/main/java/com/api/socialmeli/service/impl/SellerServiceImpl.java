package com.api.socialmeli.service.impl;

import com.api.socialmeli.dto.SellersCountFollowersDto;
import com.api.socialmeli.entity.Buyer;
import com.api.socialmeli.entity.Seller;
import com.api.socialmeli.exception.NotFoundException;
import com.api.socialmeli.repository.IBuyerRepository;
import com.api.socialmeli.repository.ISellerRepository;
import com.api.socialmeli.service.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements ISellerService {


    @Autowired
    ISellerRepository iSellerRepository;

    @Autowired
    IBuyerRepository iBuyerRepository;

    /**
     * Obtenemos la cuenta de las personas que siguen a un determinado vendedor
     * @param user_id
     * @return
     */
    @Override
    public SellersCountFollowersDto getCountOfSellerFollowers(Integer user_id) {
        //Obtenemos la lista de los vendedores y compradores
        List<Seller> sellerList = iSellerRepository.getAll();
        List<Buyer> buyerList = iBuyerRepository.getAll();

        SellersCountFollowersDto sellersCountFollowersDto = new SellersCountFollowersDto();
        int count = 0;
        boolean found = false;

        // Iteramos la lista de vendedores para saber si existe el id
        for (Seller seller : sellerList) {
            if (seller.getUser_id().equals(user_id)){
                // Seteamos los valores de user_name y user_id
                sellersCountFollowersDto.setUser_name(seller.getUser_name());
                sellersCountFollowersDto.setUser_id(seller.getUser_id());
                found = true;
                break;
            }

        }

        // Si no encontramos el id mandamos un NotFoundException
        if (!found) {
           throw new NotFoundException("There is no seller with that id");
        }

        // Por cada comprador en nuestra lista tenemos otra lista de vendedores que sigue, por lo que iteramos sobre ella
        // Y sumamos un 1 a la cuenta si es que el id coincide con el user_id de la lista de vendedores.
        for (Buyer buyer : buyerList){
            for (Seller seller : buyer.getFollowed()){
                if (seller.getUser_id().equals(user_id)) {
                    count ++;
                }
            }
        }

        // Seteamos la cuenta total a nuestra instancia
        sellersCountFollowersDto.setFollowers_count(count);

        // Devolvemos nuestro objeto
        return sellersCountFollowersDto;

    }
}
