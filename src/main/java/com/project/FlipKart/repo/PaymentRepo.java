package com.project.FlipKart.repo;

import com.project.FlipKart.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment,Integer> {
    Payment findByOrderId(int orderId);
}
