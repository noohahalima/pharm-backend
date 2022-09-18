package com.code.ordersmicroservice.dto;

import com.code.ordersmicroservice.entity.Cart;
import com.code.ordersmicroservice.entity.Drugs;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    private String id;
    private Integer quantity;
    private Drugs drugs;


    public CartItemDto(Cart cart) {
        this.id = cart.getId();
        this.quantity = cart.getQuantity();
        this.setDrugs(cart.getDrug());
    }
}