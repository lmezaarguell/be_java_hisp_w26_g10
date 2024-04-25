package com.api.socialmeli.controller;
import java.util.List;

import com.api.socialmeli.repository.ISellerRepository;
import com.api.socialmeli.service.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.socialmeli.entity.Buyer;
import com.api.socialmeli.service.IBuyerService;

@RestController
public class SocialMeliController {

    @Autowired
    IBuyerService buyerService;
    @Autowired
    ISellerService iSellerService;

    @GetMapping("/users")
    public ResponseEntity<List<Buyer>> getAll(){
        return new ResponseEntity<List<Buyer>>(buyerService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<Buyer> followUser(@PathVariable Integer userId, @PathVariable Integer userIdToFollow){
        return new ResponseEntity<Buyer>(buyerService.followUser(userId, userIdToFollow), HttpStatus.OK);
    }


    @GetMapping("/users/{userId}/followers/count")
    public ResponseEntity<?> getCountOfSellerFollowers(@PathVariable Integer userId){
        return new ResponseEntity<>(iSellerService.getCountOfSellerFollowers(userId), HttpStatus.OK);
    }
    /*
    US 0007: endpoint
    */
    @PostMapping("/users/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<?> unfollowUser(@PathVariable Integer userId,
                                          @PathVariable Integer userIdToUnfollow){
        buyerService.unfollowUser(userId,userIdToUnfollow);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
