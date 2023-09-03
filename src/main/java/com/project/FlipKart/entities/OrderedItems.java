package com.project.FlipKart.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="ordered_items")
public class OrderedItems {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int serialno;
    private int orderId;

    private int productId;
    private int quantity;
    private double price;

    public int getSerialno() {
        return serialno;
    }

    public void setSerialno(int serialno) {
        this.serialno = serialno;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }



    @ManyToOne
    @JoinColumn(name="orderId",referencedColumnName = "orderId",insertable = false,updatable = false)
    @JsonIgnore
    private Orders orders;

    @ManyToOne
    @JoinColumn(name="productId",referencedColumnName = "productId",insertable = false,updatable = false)
    @JsonIgnore
    private Products products;
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductsId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
