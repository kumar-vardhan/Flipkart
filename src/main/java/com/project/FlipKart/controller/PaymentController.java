package com.project.FlipKart.controller;

import com.project.FlipKart.entities.Payment;
import com.project.FlipKart.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/allpayments")
    public List<Payment> allPayments(){
        return  paymentService.allPayments();
    }
}
