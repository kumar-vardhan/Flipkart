package com.project.FlipKart.service;

import com.project.FlipKart.entities.Order;
import com.project.FlipKart.entities.OrderedItem;
import com.project.FlipKart.entities.Product;
import com.project.FlipKart.entities.User;
import com.project.FlipKart.enums.OrderStatus;
import com.project.FlipKart.exception.UserDefinedException;
import com.project.FlipKart.repo.OrderedItemsRepo;
import com.project.FlipKart.repo.OrdersRepo;
import com.project.FlipKart.repo.ProductsRepo;
import com.project.FlipKart.repo.UsersRepo;
import com.project.FlipKart.serviceInterface.OrderedItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderedItemServiceImpl implements OrderedItemsService {

    @Autowired
    private OrderedItemsRepo orderedItemsRepo;

    @Autowired
    private ProductsRepo productsRepo;

    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private UsersRepo usersRepo;

    @Override
    public List<OrderedItem> getOrderedItems(){
        return orderedItemsRepo.findAll();
    }






}
