package com.code.ordersmicroservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class ShoppingCart {
    @Id
    private int id;

    private int drugId;
    private String drugName;

    private int quantity;
    private float amount;



}
