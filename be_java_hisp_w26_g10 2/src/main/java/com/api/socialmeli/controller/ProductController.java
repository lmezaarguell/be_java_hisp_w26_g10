package com.api.socialmeli.controller;

import com.api.socialmeli.dto.PostDto;
import com.api.socialmeli.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    IPostService postService;

    @PostMapping("/products/post")
    public ResponseEntity<?> NewPost(@RequestBody PostDto postDto){
        return new ResponseEntity(this.postService.publishPost(postDto), HttpStatus.OK);
    }

}
