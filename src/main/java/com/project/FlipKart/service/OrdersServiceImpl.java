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
public class OrdersServiceImpl implements OrderService {

    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private PaymentRepo paymentRepo;

    @Override
    public List<Orders> allOrders(){
        return  ordersRepo.findAll();
    }

    @Override
    public  List<Orders> getOrders(String usernanme) throws UserDefinedException {
        Users users1 = usersRepo.findByusername(usernanme);
        if(users1==null){
            throw new UserDefinedException("Invaild user name");
        }
        return ordersRepo.getByUserId(users1.getUserId());

    }

    @Override
    public String cancelOrder(int orderId,int userId) throws UserDefinedException {
        Optional<Orders> orders = ordersRepo.findById(orderId);

        Optional<Users> users = usersRepo.findById(userId);
        if(orders.isEmpty()){
            throw new UserDefinedException("Invalid OrderId");
        }
        Orders orders1 = orders.get();
        if(userId!=orders1.getUserId()){
            throw new UserDefinedException("Invalid user trying to cancel the order");
        }

        Payment payment = paymentRepo.findByOrderId(orderId);
        if(payment==null){
            orders1.setOrderStatus("cancelled");
            ordersRepo.save(orders1);
            throw new UserDefinedException("Order cancelled but No payment found");
        }
        orders1.setOrderStatus("cancelled");
        Users users1 = users.get();
        users1.setWallet(users1.getWallet()+orders1.getTotalAmount());
        ordersRepo.save(orders1);
        usersRepo.save(users1);
        return "Order cancelled and money added to wallet";
    }
}
