package com.project.FlipKart.controller;

import com.project.FlipKart.entities.Payment;
import com.project.FlipKart.exception.UserDefinedException;
import com.project.FlipKart.service.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaymentController {

    @Autowired
    private PaymentServiceImpl paymentServiceImpl;

    @GetMapping("/allpayments")
    public List<Payment> allPayments(){
        return  paymentServiceImpl.allPayments();
    }

    @PostMapping("/makeAnPayment")
    public Payment makeAnPayment(@RequestParam("orderId")int orderId,@RequestParam("userId")int userId) throws UserDefinedException {
        return  paymentServiceImpl.makePayment(orderId,userId);
    }
}
