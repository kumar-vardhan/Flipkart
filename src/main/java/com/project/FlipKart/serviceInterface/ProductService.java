package com.project.FlipKart.serviceInterface;

import com.project.FlipKart.entities.Product;
import com.project.FlipKart.exception.UserDefinedException;

import java.util.List;

public interface ProductService {

    public List<Product> getProducts();
    public Product addProduct(Product product);
    public void deleteProduct(int productId) throws UserDefinedException;
    public Product updateProductPrice(int productId, double price) throws UserDefinedException;

    public List<Product> getProductsByCategory(String category) throws UserDefinedException;
}
