package com.example.payment.service.serviceImpl;

import org.springframework.stereotype.Service;

import com.example.payment.repository.PaymentRepository;
import com.example.payment.repository.repositoryImpl.PaymentRepositoryImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl {
    private final PaymentRepository paymentRepository;
    private final PaymentRepositoryImpl paymentRepositoryImpl;

}
