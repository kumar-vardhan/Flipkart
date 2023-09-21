package com.project.FlipKart.controller;

import com.project.FlipKart.entities.Order;
import com.project.FlipKart.entities.OrderedItem;
import com.project.FlipKart.entities.User;
import com.project.FlipKart.exception.UserDefinedException;
import com.project.FlipKart.repo.UsersRepo;
import com.project.FlipKart.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @Autowired
    private UsersRepo usersRepo;

    @GetMapping("/all-orders")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Order> getAllOrders(){
        return orderServiceImpl.getOrders();
    }

    @GetMapping("/orders")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public  List<Order> getOrders() throws UserDefinedException {
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        User user1 = usersRepo.findByUsername(username);
        return orderServiceImpl.getOrders(user1.getUserId());
    }

    @PostMapping("/order-items")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public  String orderItem(@RequestBody List<OrderedItem> orderedItems) throws UserDefinedException {
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        User user1 = usersRepo.findByUsername(username);
        orderServiceImpl.placeOrder(orderedItems, user1.getUserId());
        return "Order placed successfully";
    }
    @DeleteMapping("/orders/{order-id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String cancelOrder(@PathVariable("order-id")int orderId)throws UserDefinedException{
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        User user1 = usersRepo.findByUsername(username);
        return orderServiceImpl.cancelOrder(orderId, user1.getUserId());
    }

}
