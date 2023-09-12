package com.project.FlipKart.service;

import com.project.FlipKart.entities.Order;
import com.project.FlipKart.entities.Payment;
import com.project.FlipKart.entities.User;
import com.project.FlipKart.enums.OrderStatus;
import com.project.FlipKart.exception.UserDefinedException;
import com.project.FlipKart.repo.OrdersRepo;
import com.project.FlipKart.repo.PaymentRepo;
import com.project.FlipKart.repo.UsersRepo;
import com.project.FlipKart.serviceInterface.PaymentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public List<Payment> getPayments(){
        return  paymentRepo.findAll();
    }

    @Override
    public Payment makePayment(int orderId, int userId) throws UserDefinedException {

        Payment payment = new Payment();
        Optional<User> users = usersRepo.findById(userId);
        Optional<Order> orders1 = ordersRepo.findById(orderId);

        if(orders1.isEmpty()){
            throw new UserDefinedException("Invalid order id");
        }
        Order order = orders1.get();
        if(order.getOrderStatus().equals(OrderStatus.PACKED)){
            throw new UserDefinedException("Payment Already Done");
        }
        User user1 = users.get();
        if(order.getUserId()!=userId){
            throw new UserDefinedException("Invalid users making payment");
        }
        if(user1.getWallet()<payment.getBillAmount()){
            throw new UserDefinedException("Amount in wallet is insufficient");
        }
        payment.setBillAmount(order.getTotalAmount());
        user1.setWallet(user1.getWallet()- order.getTotalAmount());
        payment.setOrderId(orderId);
        payment.setCreatedAt(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.PACKED);
        usersRepo.save(user1);
        ordersRepo.save(order);
        return paymentRepo.save(payment);
    }
}
