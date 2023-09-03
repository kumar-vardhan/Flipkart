package com.project.FlipKart.service;

import com.project.FlipKart.entities.OrderedItems;
import com.project.FlipKart.entities.Orders;
import com.project.FlipKart.entities.Products;
import com.project.FlipKart.entities.Users;
import com.project.FlipKart.repo.OrderedItemsRepo;
import com.project.FlipKart.repo.OrdersRepo;
import com.project.FlipKart.repo.ProductsRepo;
import com.project.FlipKart.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderedItemsService {

    @Autowired
    private OrderedItemsRepo orderedItemsRepo;

    @Autowired
    private ProductsRepo productsRepo;

    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private UsersRepo usersRepo;

    public List<OrderedItems> allOrderedItems(){
        return orderedItemsRepo.findAll();
    }

    public OrderedItems additem(OrderedItems orderedItems){
        OrderedItems a = new OrderedItems();
        a.setOrderId(orderedItems.getOrderId());
        a.setProductId(orderedItems.getProductId());
        a.setPrice(orderedItems.getPrice());
        a.setQuantity(orderedItems.getQuantity());
        return orderedItemsRepo.save(a);
    }

    public void placeAnOrder(String username,String productName, int quantity){

        Products products = productsRepo.findByProductName(productName);
        Users users = usersRepo.findByusername(username);
        Orders orders = new Orders();
       // orders.setOrderId(404);
        System.out.println(orders.getOrderId());
        //ordersRepo.save(orders);

        OrderedItems orderedItems = new OrderedItems();
        orderedItems.setProductId(products.getProductId());
        orderedItems.setQuantity(quantity);
        orderedItems.setOrderId(orders.getOrderId());
        orderedItems.setPrice(products.getPrice());

       // orderedItems.setProducts(products);
        orderedItemsRepo.save(orderedItems);

        orders.setUserId(users.getUserId());
        orders.setTotalAmount(orderedItems.getPrice()*quantity);
        orders.setOrderStatus("packed");

        orders.setOrderedItems((List<OrderedItems>) orderedItems.getProducts().getOrderedItems());
        ordersRepo.save(orders);
        orderedItemsRepo.save(orderedItems);

    }

}
