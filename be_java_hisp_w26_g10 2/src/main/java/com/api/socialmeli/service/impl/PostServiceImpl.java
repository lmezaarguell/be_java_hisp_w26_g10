package com.api.socialmeli.service.impl;

import com.api.socialmeli.dto.PostDto;
import com.api.socialmeli.entity.Post;
import com.api.socialmeli.exception.BadRequestException;
import com.api.socialmeli.repository.IPostRepository;
import com.api.socialmeli.service.IPostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements IPostService {

    // MCaldera - Inyeccion del repositorio de posts
    @Autowired
    IPostRepository postRepository;
    // MCaldera - Declaracion de array para almacenamiento de datos
    private final List<Post> posts = new ArrayList<>();
    int postId;

    // MCaldera - Funcion base de creacion de nuevos posts
    @Override
    public PostDto publishPost(PostDto postDto) {
        // generacion de consecutivo 'post_id'
        postId = getPostId();
        // seteo de consecutivo generado
        postDto.setPost_id(postId);
        // conversion de dto a objeto
        Post post = convertToPost(postDto);
        // se almacena el objeto en el array
        posts.add(post);
        // se retorna el dto generado para la respuesta
        return postDto;
    }

    // MCaldera - funcion de busqueda de 'post_id' generado en el repositorio
    private int generatePostId(){
        return this.postRepository.searchPostId();
    }

    // MCaldera - Metodo main para el retorno del objeto
    private Post convertToPost(PostDto postDto) {
        try {
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
            return mapper.convertValue(postDto, Post.class);
        } catch (Exception e) {
            throw new BadRequestException(e + "Reviewed data");
        }
    }

    // MCaldera - Funcion que genera consecutivo de 'post_id'
    private int getPostId() {
        // se valida si hay informacion almacenada en el arreglo
        if (posts.isEmpty()){
            // llamado de funcion que toma informacion del repositorio
            postId = generatePostId();
        }else {
            // se obtiene el dato mas reciente del arreglo para generar consecutivo
            postId = (posts.stream().mapToInt(Post::getPost_id).max().orElse(0) +1);
        }
        return postId;
    }
}
