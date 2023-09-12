package com.project.FlipKart.service;

import com.project.FlipKart.entities.Product;
import com.project.FlipKart.exception.UserDefinedException;
import com.project.FlipKart.repo.ProductsRepo;
import com.project.FlipKart.serviceInterface.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductsRepo productsRepo;

    @Override
    public List<Product> getProducts(){
        return  productsRepo.findAll();
    }

    @Override
    public Product addProduct(Product product){
        Product products = new Product();
        products.setProductName(product.getProductName());
        products.setCategory(product.getCategory());
        products.setPrice(product.getPrice());
        products.setCreatedAt(LocalDateTime.now());
        products.setUpdatedAt(LocalDateTime.now());
        return productsRepo.save(products);
    }

    @Override
    public void deleteProduct(int productId) throws UserDefinedException {
        Optional<Product> products = productsRepo.findById(productId);

        if(products.isEmpty())
            throw new UserDefinedException("Invalid Products id");
        productsRepo.deleteById(productId);
    }

    @Override
    public Product updateProductPrice(int productId, double price) throws UserDefinedException {

        Optional<Product> products = productsRepo.findById(productId);
        if(products.isEmpty())
            throw new UserDefinedException("Invalid Product id");
        Product product1 = products.get();
        product1.setPrice(price);
        product1.setUpdatedAt(LocalDateTime.now());
        productsRepo.save(product1);
        return product1;
    }

    @Override
    public List<Product> getProductsByCategory(String category) throws UserDefinedException {
        List<Product> products = productsRepo.findByCategory(category);
        if(products.isEmpty()){
            throw new UserDefinedException("Invalid Category Name");
        }
         return products;

    }
}
