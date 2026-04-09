package com.example.payment.service.serviceImpl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.payment.dto.PaymentRequest;
import com.example.payment.entity.Payment;
import com.example.payment.repository.PaymentRepository;
import com.example.payment.repository.repositoryImpl.PaymentRepositoryImpl;
import com.example.payment.service.PaymentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor  // <-- Automatically injects final fields
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentRepositoryImpl paymentRepositoryImpl; // optional, only if using custom queries

    @Override
    public Payment createPayment(PaymentRequest request) {
        Payment payment = Payment.builder()
                .transactionId(UUID.randomUUID().toString())
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .paymentMethod(request.getPaymentMethod())
                .status("PENDING")
                .createdAt(LocalDateTime.now())
                .build();

        return paymentRepository.save(payment);
    }

    @Override
    public Payment processPayment(String transactionId) {
        Payment payment = paymentRepository.findByTransactionId(transactionId);
        if (payment == null) {
            throw new RuntimeException("Payment not found");
        }

        String status = payment.getAmount() > 0 ? "SUCCESS" : "FAILED";

        // Option 1: Use JPA save()
        payment.setStatus(status);
        paymentRepository.save(payment);

        // Option 2: Use custom SQL (optional)
        // paymentRepositoryImpl.updatePaymentStatus(transactionId, status);

        return payment;
    }

    @Override
    public Payment getPayment(String transactionId) {
        Payment payment = paymentRepository.findByTransactionId(transactionId);
        if (payment == null) {
            throw new RuntimeException("Payment not found");
        }
        return payment;
    }
}