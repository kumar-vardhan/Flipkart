package com.project.FlipKart.controller;

import com.project.FlipKart.entities.Payment;
import com.project.FlipKart.entities.User;
import com.project.FlipKart.exception.UserDefinedException;
import com.project.FlipKart.repo.UsersRepo;
import com.project.FlipKart.service.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaymentController {

    @Autowired
    private PaymentServiceImpl paymentServiceImpl;
    @Autowired
    private UsersRepo usersRepo;

    @GetMapping("/payments")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Payment> getPayments(){
        return  paymentServiceImpl.getPayments();
    }

    @PostMapping("/pay")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Payment payIt(@RequestParam("order-id")int orderId) throws UserDefinedException {
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        User user1 = usersRepo.findByUsername(username);
        return  paymentServiceImpl.makePayment(orderId, user1.getUserId());
    }

}
