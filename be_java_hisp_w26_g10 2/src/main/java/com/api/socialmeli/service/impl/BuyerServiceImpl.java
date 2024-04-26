package com.api.socialmeli.service.impl;

import com.api.socialmeli.entity.Buyer;
import com.api.socialmeli.entity.Seller;
import com.api.socialmeli.exception.BadRequestException;
import com.api.socialmeli.repository.IBuyerRepository;
import com.api.socialmeli.repository.ISellerRepository;
import com.api.socialmeli.dto.BuyerFollowedListDTO;
import com.api.socialmeli.exception.NotFoundException;
import com.api.socialmeli.service.IBuyerService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
@Service
public class BuyerServiceImpl implements IBuyerService {
    public static String orderAsc = "name_asc";
    public static String orderDesc = "name_desc";

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
        Buyer userFollowing = buyerRepository.getById(userId);
        if(userFollowed == null || userFollowing == null){
            throw new BadRequestException("Comprador o vendedor no encontrado por Id");
        }
        if(userFollowing.getFollowed().contains(userFollowed)){
            throw new BadRequestException("Ya esta siguiendo al comprador");
        }
        return buyerRepository.followUser(userFollowing, userFollowed);
    }

    @Override
    public Buyer getBuyerById(Integer id) {
        Buyer buyer = buyerRepository.getById(id);
        //Valida que sea un usario registrado y retorna el cliente
        if (buyer.equals(null))
            throw new NotFoundException("El usuario no existe o no se encuentra registrado.");
        return buyer;
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
    //Servicio que implementa la logica para obtener la lista de todos los vendedores que sigue
    //un determinado usuario con la opcion de poder ordenarlo por nombre ascendente o descentente
    @Override
    public BuyerFollowedListDTO GetFollowedListByUser(Integer user_id, String order) {
        ObjectMapper mapper = new ObjectMapper();
        //Se obtiene el usuario solicitado
        Buyer buyer = getBuyerById(user_id);
        //Determina si se recibio un ordenamiento y si es correcto realiza la llamada al metodo para ordenar por nombre
        if (order != null)
            buyer.setFollowed(OrderFollowedListByName(order,buyer.getFollowed()));
        //Retorna la salida solicitada
        return mapper.convertValue(buyer,BuyerFollowedListDTO.class);
    }

    //Metodo que ordena segun los lineamientos descritos en el US0008
    public List<Seller> OrderFollowedListByName(String order, List<Seller> sellers){
        //Ordenamiento ascendente mediante expresiones lambda
        if (order.equals(orderAsc)){
            return ((sellers.stream()
                    .sorted(Comparator.comparing(Seller::getUser_name)).toList()));
        }
        //Ordenamiento descendente mediante expresiones lambda
        if (order.equals(orderDesc)){
            return ((sellers.stream()
                    .sorted(Comparator.comparing(Seller::getUser_name).reversed()).toList()));
        }
        //Si no se encuentra el ordenamiento solicitado en el US0008 entonces lanza la excepción BadRequest
        throw new BadRequestException("Parametros incorrectos para el ordenamiento");
    }
}
