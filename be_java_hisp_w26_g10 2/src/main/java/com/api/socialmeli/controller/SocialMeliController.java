package com.api.socialmeli.controller;
import java.util.List;
import com.api.socialmeli.service.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getCountOfSellerFollowers(@PathVariable int userId){
        return new ResponseEntity<>(iSellerService.getCountOfSellerFollowers(userId), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/followers/list")
    public ResponseEntity<?> getFollowersOfSeller(
        @PathVariable("userId") int userId,
        @RequestParam(name = "order", defaultValue = "", required = false) String order)
    {
        return new ResponseEntity<>(iSellerService.getFollowersOfSeller(userId, order), HttpStatus.OK);
    }
}
