package com.code.ordersmicroservice.dto;


public class CheckoutItemDto {

    private String drugName;
    private int  quantity;
    private double price;
    private String drugId;
    private String userId;

    public CheckoutItemDto() {}

    public CheckoutItemDto(String drugName, int quantity, double price, String drugId, String userId) {
        this.drugName = drugName;
        this.quantity = quantity;
        this.price = price;
        this.drugId = drugId;
        this.userId = userId;
    }

    //getter and setter are removed for sake of brevity

}