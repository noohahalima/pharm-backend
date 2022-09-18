package com.code.ordersmicroservice.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
@AllArgsConstructor
@Getter
@Setter
@Document
public class Cart {

    @Id
    private String id;

    @Field
    private String createdDate;

    @Field
    private Drugs drug;

    @Field
    private User user;
    @Field
    private int quantity;


    public Cart() {
    }


}