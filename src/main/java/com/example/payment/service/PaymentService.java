package com.example.payment.service;

import com.example.payment.dto.PaymentRequest;
import com.example.payment.entity.Payment;

public interface PaymentService {
    
    Payment createPayment(PaymentRequest request);

    Payment processPayment(String transactionId);

    Payment getPayment(String transactionId);
}
