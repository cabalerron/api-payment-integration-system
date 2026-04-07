package com.example.payment.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private Double amount;
    private String currency;
    private String paymentMethod;
}
