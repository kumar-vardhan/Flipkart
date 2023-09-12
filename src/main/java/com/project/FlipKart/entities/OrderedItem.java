package com.project.FlipKart.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="ordered_items")
public class OrderedItem {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int serialno;
    private int orderId;

    private int productId;
    private int quantity;
    private double price;




    @ManyToOne
    @JoinColumn(name="orderId",referencedColumnName = "orderId",insertable = false,updatable = false)
    @JsonIgnore
    private Order order;

    @ManyToOne
    @JoinColumn(name="productId",referencedColumnName = "productId",insertable = false,updatable = false)
    @JsonIgnore
    private Product product;

    public int getSerialno() {
        return serialno;
    }

    public void setSerialno(int serialno) {
        this.serialno = serialno;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
