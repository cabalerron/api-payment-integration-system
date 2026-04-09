package com.example.payment.repository.repositoryImpl;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class PaymentRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    // Custom SQL update
    public void updatePaymentStatus(String transactionId, String status) {
        entityManager.createNativeQuery("UPDATE payment SET status = ? WHERE transaction_id = ?")
                .setParameter(1, status)
                .setParameter(2, transactionId)
                .executeUpdate();
    }
}