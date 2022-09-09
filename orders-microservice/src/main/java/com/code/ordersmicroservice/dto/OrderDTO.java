package com.code.ordersmicroservice.dto;

import com.code.ordersmicroservice.entity.ShoppingCart;
import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO {
    private List<ShoppingCart> cartItems;
    private String userEmail;
    private String userName;
}
