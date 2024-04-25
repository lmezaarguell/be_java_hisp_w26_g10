package com.api.socialmeli.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.socialmeli.entity.Buyer;

@RestController
public class SocialMeliController {

    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
        public ResponseEntity<Buyer> followUser(@PathVariable Integer userId, @PathVariable Integer userIdToFollow){
            return null;
        }
}
