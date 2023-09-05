package com.project.FlipKart.service;

import com.project.FlipKart.entities.OrderedItems;
import com.project.FlipKart.entities.Orders;
import com.project.FlipKart.entities.Products;
import com.project.FlipKart.entities.Users;
import com.project.FlipKart.exception.UserDefinedException;
import com.project.FlipKart.repo.OrderedItemsRepo;
import com.project.FlipKart.repo.OrdersRepo;
import com.project.FlipKart.repo.ProductsRepo;
import com.project.FlipKart.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderedItemsService implements OrderedItemsServiceImpl{

    @Autowired
    private OrderedItemsRepo orderedItemsRepo;

    @Autowired
    private ProductsRepo productsRepo;

    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private UsersRepo usersRepo;

    @Override
    public List<OrderedItems> allOrderedItems(){
        return orderedItemsRepo.findAll();
    }


    @Override
    public void placeAnOrder(String username,String productName, int quantity) throws UserDefinedException {
        Products products = productsRepo.findByProductName(productName);
        if(products==null){
            throw new UserDefinedException("Product not found");
        }
        Users users = usersRepo.findByusername(username);
       if(users==null){
           throw  new UserDefinedException("Invalid User");
       }


        Orders orders = new Orders();
        orders.setUserId(users.getUserId());
        orders.setTotalAmount(products.getPrice()*quantity);
        orders.setOrderStatus("packed");
        orders.setOrderedAt(LocalDateTime.now());
        ordersRepo.save(orders);

        OrderedItems orderedItems = new OrderedItems();
        orderedItems.setProductId(products.getProductId());
        orderedItems.setQuantity(quantity);
        orderedItems.setOrderId(orders.getOrderId());
        orderedItems.setPrice(products.getPrice());

        orderedItemsRepo.save(orderedItems);



    }


}
