package com.api.socialmeli.controller;

import com.api.socialmeli.service.IBuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SocialMeliController {

    @Autowired
    IBuyerService buyerService;

    @GetMapping("/users/{userId}/followed/list")
    public ResponseEntity<?> getFollowedListById(@PathVariable Integer userId,@RequestParam(required = false) String order){
        if (order==null)
            return ResponseEntity.status(HttpStatus.OK).body(buyerService.getFollowedListByUser(userId));
        return ResponseEntity.status(HttpStatus.OK).body(buyerService.getFollowedListByUserOrderByName(userId,order));
    }


}
