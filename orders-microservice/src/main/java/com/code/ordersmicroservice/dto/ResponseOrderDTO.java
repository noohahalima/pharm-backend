package com.code.ordersmicroservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseOrderDTO {

    private float amount;
    private int invoiceNumber;
    private String date;
    private String orderId;

}
