package com.api.socialmeli.service.impl;

import com.api.socialmeli.dto.PostDto;
import com.api.socialmeli.dto.PostsByFollowedDto;
import com.api.socialmeli.entity.Buyer;
import com.api.socialmeli.entity.Post;
import com.api.socialmeli.entity.Seller;
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
    public PostsByFollowedDto getPostsByFollowed(Integer userId, boolean orderDes) {
        Buyer buyer = buyerService.getBuyerById(userId);

        if (buyer == null) {
            throw new NotFoundException("No se encontro el usuario con id: " + userId);
        }

        PostsByFollowedDto postByFollowedDto = new PostsByFollowedDto();
        postByFollowedDto.setUser_id(userId);

        List<Integer> ids = buyer.getFollowed().stream().map(Seller::getUser_id).toList();
        if (ids.isEmpty()) {
            postByFollowedDto.setPosts(new ArrayList<>());
            return postByFollowedDto;
        }
        LocalDate minDate = LocalDate.now().minusDays(15);
        LocalDate maxDate = LocalDate.now().plusDays(1);

        List<Post> postsFollowed;
        if(orderDes) {
            postsFollowed = postRepository.getAll().stream().
                    filter(post -> findIdInPost(post.getUser_id(), ids) && post.getDate().isAfter(minDate)
                            && post.getDate().isBefore(maxDate)).sorted(Comparator.comparing(Post::getDate).reversed()).toList();
        }else{
            postsFollowed = postRepository.getAll().stream().
                    filter(post -> findIdInPost(post.getUser_id(), ids) && post.getDate().isAfter(minDate)
                            && post.getDate().isBefore(maxDate)).sorted(Comparator.comparing(Post::getDate)).toList();
        }

        if (postsFollowed.isEmpty()) {
            postByFollowedDto.setPosts(new ArrayList<>());
            return postByFollowedDto;
        }

        List<PostDto> posts = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        for (Post post : postsFollowed) {
            System.out.println(post);
            PostDto postDto = mapper.convertValue(post, PostDto.class);
            posts.add(postDto);
        }

        postByFollowedDto.setPosts(posts);

        return postByFollowedDto;
    }


    private boolean findIdInPost(Integer idUser, List<Integer> ids) {
        return ids.stream().anyMatch(id -> id.equals(idUser));
    }
}
