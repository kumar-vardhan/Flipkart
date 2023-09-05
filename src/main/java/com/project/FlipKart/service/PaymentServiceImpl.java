package com.project.FlipKart.service;

import com.project.FlipKart.entities.Orders;
import com.project.FlipKart.entities.Payment;
import com.project.FlipKart.entities.Users;
import com.project.FlipKart.exception.UserDefinedException;
import com.project.FlipKart.repo.OrdersRepo;
import com.project.FlipKart.repo.PaymentRepo;
import com.project.FlipKart.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentServices {

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private OrdersRepo ordersRepo;

    @Override
    public List<Payment> allPayments(){
        return  paymentRepo.findAll();
    }

    @Override
    public Payment makePayment(int orderId, int userId) throws UserDefinedException {

        Payment payment = new Payment();
        Optional<Users> users = usersRepo.findById(userId);
        Optional<Orders> orders1 = ordersRepo.findById(orderId);
        if(orders1.isEmpty()){
            throw new UserDefinedException("Invalid order id");
        }
        Orders orders = orders1.get();
        Users users1 = users.get();
        if(orders.getUserId()!=userId){
            throw new UserDefinedException("Invalid users making payment");
        }
        if(users1.getWallet()<payment.getBillAmount()){
            throw new UserDefinedException("Amount in wallet is insufficient");
        }
        payment.setBillAmount(orders.getTotalAmount());
        users1.setWallet(users1.getWallet()-orders.getTotalAmount());
        payment.setOrderId(orderId);

        usersRepo.save(users1);
        return paymentRepo.save(payment);
    }
}
