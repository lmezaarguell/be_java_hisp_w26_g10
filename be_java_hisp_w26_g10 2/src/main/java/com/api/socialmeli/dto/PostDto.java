package com.api.socialmeli.dto;

import com.api.socialmeli.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private int user_id;
    private int post_id;
    private LocalDate date;
    private Product product;
    private int category;
    private double price;
}
