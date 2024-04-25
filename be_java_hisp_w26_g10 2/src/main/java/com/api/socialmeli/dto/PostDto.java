package com.api.socialmeli.dto;

import com.api.socialmeli.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private int post_id;
    private int user_id;
    private Date date;
    private Product product;
    private int category;
    private double price;
}
