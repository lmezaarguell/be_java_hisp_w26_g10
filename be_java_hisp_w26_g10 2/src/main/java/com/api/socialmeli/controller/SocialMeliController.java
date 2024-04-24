package com.api.socialmeli.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class SocialMeliController {

    @Autowired
    IBuyerService buyerService;
    ISellerService iSellerService;

    @GetMapping("/users")
    public ResponseEntity<List<Buyer>> getAll(){
        return new ResponseEntity<List<Buyer>>(buyerService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<Buyer> followUser(@PathVariable Integer userId, @PathVariable Integer userIdToFollow){
        return new ResponseEntity<Buyer>(buyerService.followUser(userId, userIdToFollow), HttpStatus.OK);
    }


    //Se realiza la función del controller para direccionar el endpoint 4 y el respectivo 8 del API
    @GetMapping("/users/{userId}/followed/list")
    public ResponseEntity<?> getFollowedListById(@PathVariable Integer userId,@RequestParam(required = false) String order){
        return ResponseEntity.status(HttpStatus.OK).body(buyerService.getFollowedListByUser(userId,order));
    }

    @GetMapping("/users/{userId}/followers/count")
    public ResponseEntity<?> getCountOfSellerFollowers(@PathVariable int userId){
        return new ResponseEntity<>(iSellerService.getCountOfSellerFollowers(userId), HttpStatus.OK);
    }
    @Autowired
    IBuyerService buyerService;

    @GetMapping("/users/{userId}/followed/list")
    public ResponseEntity<?> getFollowedListById(@PathVariable Integer userId){
        return ResponseEntity.status(HttpStatus.OK).body(buyerService.getFolloedListByUser(userId));
    }

}
