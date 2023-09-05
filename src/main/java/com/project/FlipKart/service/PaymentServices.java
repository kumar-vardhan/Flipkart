package com.project.FlipKart.service;

import com.project.FlipKart.entities.Payment;
import com.project.FlipKart.exception.UserDefinedException;

import java.util.List;

public interface PaymentServices {

    public List<Payment> allPayments();
    public Payment makePayment(int orderId, int userId) throws UserDefinedException;
}
