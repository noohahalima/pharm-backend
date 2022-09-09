package com.code.ordersmicroservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document
public class Order {
    @Id
    private String orderId;
    @Field
    private User user;
    @Field
    private String pickupDate;

    private List<ShoppingCart> cartItems;

    public Order(User user, List<ShoppingCart> cartItems) {
        this.user = user;
        this.cartItems = cartItems;
    }
}
