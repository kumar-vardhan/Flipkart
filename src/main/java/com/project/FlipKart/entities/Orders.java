package com.project.FlipKart.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    private int userId;
    private double totalAmount;

    private  String orderStatus;

    private LocalDateTime orderedAt;

    @ManyToOne
    @JoinColumn (name="userId", referencedColumnName = "userId", insertable = false, updatable = false)
    @JsonIgnore
    private Users users;

    @OneToMany(mappedBy = "orders")
    @JsonIgnore
    private List<OrderedItems> orderedItems;

    @OneToOne(mappedBy = "orders")
    @JsonIgnore
    private Payment payment;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getOrderedAt() {
        return orderedAt;
    }

    public void setOrderedAt(LocalDateTime orderedAt) {
        this.orderedAt = orderedAt;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public List<OrderedItems> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<OrderedItems> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
