package com.api.socialmeli.dto;

import com.api.socialmeli.entity.Seller;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyerFollowedListDTO {
    private Integer user_id;
    private String user_name;
    private List<Seller> followed;
}
