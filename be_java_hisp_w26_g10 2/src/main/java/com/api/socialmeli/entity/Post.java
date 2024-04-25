package com.api.socialmeli.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private int post_id;
    private LocalDate date;
    private int category;
    private double price;
    private int user_id;
    private Product product;

    @JsonIgnore
    private boolean has_promo;
    @JsonIgnore
    private double discount;

}
