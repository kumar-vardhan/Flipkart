package com.project.FlipKart.service;

import com.project.FlipKart.entities.Products;
import com.project.FlipKart.exception.UserDefinedException;
import com.project.FlipKart.repo.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsServiceImpl implements ProductService {

    @Autowired
    private ProductsRepo productsRepo;

    @Override
    public List<Products> allProducts(){
        return  productsRepo.findAll();
    }

    @Override
    public Products addProduct(Products product){
        Products products = new Products();
        products.setProductName(product.getProductName());
        products.setCategory(product.getCategory());
        products.setPrice(product.getPrice());

        return productsRepo.save(products);
    }

    @Override
    public void deleteProduct(int productId) throws UserDefinedException {
        Optional<Products> products = productsRepo.findById(productId);

        if(products.isEmpty())
            throw new UserDefinedException("Invalid Products id");
        productsRepo.deleteById(productId);
    }

    @Override
    public Products updatePrice(int productId, double price) throws UserDefinedException {

        Optional<Products> products = productsRepo.findById(productId);
        if(products.isEmpty())
            throw new UserDefinedException("Invalid Product id");
        Products products1 = products.get();
        products1.setPrice(price);
        productsRepo.save(products1);

        return products1;
    }

    @Override
    public List<Products> getProductsByCategory(String category) throws UserDefinedException {
        List<Products> products = productsRepo.findByCategory(category);
        if(products.isEmpty()){
            throw new UserDefinedException("Invaild Category Name");
        }
         return products;

    }
}
