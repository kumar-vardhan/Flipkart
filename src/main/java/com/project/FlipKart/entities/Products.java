package com.project.FlipKart.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    private String productName;
    private double price;
    private String category;

    @OneToMany(mappedBy = "products")
    @JsonIgnore
    private List<OrderedItems> orderedItems;

    public List<OrderedItems> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<OrderedItems> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
