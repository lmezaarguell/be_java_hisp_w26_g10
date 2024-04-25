package com.api.socialmeli.service.impl;

import com.api.socialmeli.dto.PostDto;
import com.api.socialmeli.dto.PostsByFollowedDto;
import com.api.socialmeli.entity.Buyer;
import com.api.socialmeli.entity.Post;
import com.api.socialmeli.entity.Seller;
import com.api.socialmeli.exception.BadRequestException;
import com.api.socialmeli.exception.NotFoundException;
import com.api.socialmeli.repository.IPostRepository;
import com.api.socialmeli.service.IBuyerService;
import com.api.socialmeli.service.IPostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    private IBuyerService buyerService;

    @Autowired
    private IPostRepository postRepository;

    @Override
    public PostsByFollowedDto getPostsByFollowed(Integer userId, String order) {
        //Se busca el comprador y se guarda en una instacia de buyer con el id del usuario recibido
        Buyer buyer = buyerService.getBuyerById(userId);

        if (buyer == null) {
            //Si no se encontro el comprador lanza la excepcion Not Found Exception
            throw new NotFoundException("No se encontro el usuario con id: " + userId);
        }

        //Se crea la instancia del DTO que se va a devolver al controlador y se coloca el id del comprador en el dto
        PostsByFollowedDto postByFollowedDto = new PostsByFollowedDto();
        postByFollowedDto.setUser_id(userId);

        //Se obtine la lista de ids de los compradores que sigue el comprador
        List<Integer> ids = buyer.getFollowed().stream().map(Seller::getUser_id).toList();
        if (ids.isEmpty()) {
            //Si no esta siguiendo a nadie devuelve en dto al controlador
            postByFollowedDto.setPosts(new ArrayList<>());
            return postByFollowedDto;
        }

        //Se crean las fechas minima y maxima para comprender el periodo de los ultimos 15 dias
        LocalDate minDate = LocalDate.now().minusDays(15);
        LocalDate maxDate = LocalDate.now().plusDays(1);

        /*Se obtien todos las publicaciones, se filtra por las que fueron creadas por los vendedores que sigue
        con ayuda de la funcion finIdInPost y tambien se filtra con con las fecha minDate y maxDate para obtener
        las de los ultimos quince dias
         */
        List<Post> postsFollowed= postRepository.getAll().stream().
                filter(post -> findIdInPost(post.getUser_id(), ids) && post.getDate().isAfter(minDate)
                        && post.getDate().isBefore(maxDate)).toList();

        if (postsFollowed.isEmpty()) {
            //Si los vendedores que sigue no tiene publicaciones devuelve el dto al controlador
            postByFollowedDto.setPosts(new ArrayList<>());
            return postByFollowedDto;
        }

        //Se llama a la funcion para ordenar las publicaciones segun los el parametro de order
        postsFollowed = orderPostByDate(postsFollowed,order);

        //Se crea el mapper para transformar las publicaciones a dto
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

        //Se colocan las lista de las publicaciones transformadas en el dto
        postByFollowedDto.setPosts(
                postsFollowed.stream().map(post -> mapper.convertValue(post, PostDto.class)).toList());

        return postByFollowedDto;
    }

     /*Determina si el post es del vendedor que esta siguiendo, con la lista de ids de los vendedores que sigue
       y el id del creador de la publicacion*/
    private boolean findIdInPost(Integer idUser, List<Integer> ids) {
        return ids.stream().anyMatch(id -> id.equals(idUser));
    }

    //Ordena la lista de post por fecha segun el orden enviado por parametro
    List<Post> orderPostByDate(List<Post> posts, String order) {
        if(order == null || order.equalsIgnoreCase("date_asc")) {
             return posts.stream().sorted(Comparator.comparing(Post::getDate)).toList();
        }else if(order.equalsIgnoreCase("date_desc")) {
            return posts.stream().sorted(Comparator.comparing(Post::getDate).reversed()).toList();
        }else {
            throw new BadRequestException("El orden pedido no es valido");
        }
    }
}
