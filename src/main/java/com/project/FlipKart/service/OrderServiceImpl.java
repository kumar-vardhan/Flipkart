package com.project.FlipKart.service;

import com.project.FlipKart.entities.*;
import com.project.FlipKart.enums.OrderStatus;
import com.project.FlipKart.exception.UserDefinedException;
import com.project.FlipKart.repo.*;
import com.project.FlipKart.serviceInterface.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private OrderedItemsRepo orderedItemsRepo;

    @Autowired
    private ProductsRepo productsRepo;

    @Override
    public List<Order> getOrders(){
        return  ordersRepo.findAll();
    }

    @Override
    public  List<Order> getOrders(int userId) throws UserDefinedException {
        Optional<User> user1 = usersRepo.findById(userId);
        if(user1.isEmpty()){
            throw new UserDefinedException("Invalid user name");
        }
        User user = user1.get();
        return ordersRepo.getByUserId(userId);

    }

    public void placeOrder(List<OrderedItem> orderedItems, int userId) throws UserDefinedException {
        Optional<User> user = usersRepo.findById(userId);

        if(user.isEmpty()){
            throw  new UserDefinedException("Invalid User");
        }
        Order order = new Order();
        order.setUserId(userId);
        order.setOrderStatus(OrderStatus.PAYMENT_PENDING);
        order.setTotalAmount(0);
        order.setCreatedAt(LocalDateTime.now());
        ordersRepo.save(order);
        double totalAmount=0;
        for(OrderedItem orderedItem:orderedItems){
            OrderedItem orderedItem1 = new OrderedItem();
            Optional<Product> product = productsRepo.findById(orderedItem.getProductId());

            if(product.isEmpty()){
                throw new UserDefinedException("Product not found with productId"+ orderedItem.getProductId() );
            }
            Product product1 = product.get();

            orderedItem1.setProductId(orderedItem.getProductId());
            orderedItem1.setQuantity(orderedItem.getQuantity());
            orderedItem1.setOrderId(order.getOrderId());
            orderedItem1.setPrice(product1.getPrice());
            totalAmount = totalAmount + (product1.getPrice() * orderedItem.getQuantity());
            orderedItemsRepo.save(orderedItem1);

        }

        order.setTotalAmount(totalAmount);
        ordersRepo.save(order);

    }

    @Override
    public String cancelOrder(int orderId,int userId) throws UserDefinedException {
        Optional<Order> orders = ordersRepo.findById(orderId);

        Optional<User> users = usersRepo.findById(userId);
        if(orders.isEmpty()){
            throw new UserDefinedException("Invalid OrderId");
        }
        Order order1 = orders.get();
        if(userId!= order1.getUserId()){
            throw new UserDefinedException("Invalid user trying to cancel the order");
        }

        Payment payment = paymentRepo.findByOrderId(orderId);
        if(payment==null){

            throw new UserDefinedException("No payment found");
        }
        order1.setOrderStatus(OrderStatus.CANCELLED);
        User user1 = users.get();
        user1.setWallet(user1.getWallet()+ order1.getTotalAmount());

        ordersRepo.save(order1);
        usersRepo.save(user1);
        return "Order cancelled and money added to wallet";
    }
}
