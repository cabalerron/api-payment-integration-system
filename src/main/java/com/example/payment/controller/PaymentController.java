package com.example.payment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.payment.dto.PaymentRequest;
import com.example.payment.entity.Payment;
import com.example.payment.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    // Create a new payment
    @PostMapping
    public Payment createPayment(@RequestBody PaymentRequest request) {
        return paymentService.createPayment(request);
    }

    // Process payment by transactionId
    @PostMapping("/{transactionId}/process")
    public Payment processPayment(@PathVariable String transactionId) {
        return paymentService.processPayment(transactionId);
    }

    // Get payment by transactionId
    @GetMapping("/{transactionId}")
    public Payment getPayment(@PathVariable String transactionId) {
        return paymentService.getPayment(transactionId);
    }
}