package com.code.ordersmicroservice.dto;

import com.code.ordersmicroservice.entity.ShoppingCart;
import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@ToString
public class AddToCartDto {
    private String id;
    private  String drugId;
    private  Integer quantity;

    public AddToCartDto() {
    }

}
