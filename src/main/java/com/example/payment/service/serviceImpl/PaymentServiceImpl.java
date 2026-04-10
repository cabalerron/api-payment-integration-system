package com.example.payment.service.serviceImpl;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.payment.dto.PaymentRequest;
import com.example.payment.entity.Payment;
import com.example.payment.repository.PaymentRepository;
import com.example.payment.service.PaymentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

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

        Payment payment = paymentRepository.findByTransactionId(transactionId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        String status = payment.getAmount() > 0 ? "SUCCESS" : "FAILED";

        payment.setStatus(status);
        paymentRepository.save(payment);

        return payment;
    }

    @Override
    public Payment getPayment(String transactionId) {

        return paymentRepository.findByTransactionId(transactionId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }
}