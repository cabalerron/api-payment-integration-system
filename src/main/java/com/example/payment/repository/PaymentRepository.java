package com.example.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.payment.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // Native SQL query to find payment by transactionId
    @Query(value = "SELECT * FROM payment WHERE transaction_id = ?1", nativeQuery = true)
    Payment findByTransactionId(String transactionId);
}