package com.project.FlipKart.service;

import com.project.FlipKart.entities.Products;
import com.project.FlipKart.exception.UserDefinedException;

import java.util.List;

public interface ProductService {

    public List<Products> allProducts();
    public Products addProduct(Products product);
    public void deleteProduct(int productId) throws UserDefinedException;
    public Products updatePrice(int productId, double price) throws UserDefinedException;

    public List<Products> getProductsByCategory(String category) throws UserDefinedException;
}
