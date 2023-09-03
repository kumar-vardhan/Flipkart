package com.project.FlipKart.service;

import com.project.FlipKart.entities.Payment;
import com.project.FlipKart.repo.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    public List<Payment> allPayments(){
        return  paymentRepo.findAll();
    }
}
