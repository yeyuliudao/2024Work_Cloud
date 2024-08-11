package com.cvicse.product.controller;

import com.cvicse.product.model.Product;
import com.cvicse.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    @CircuitBreaker(name = "productService", fallbackMethod = "fallbackGetAllProducts")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    public List<Product> fallbackGetAllProducts(Exception ex) {
        return Collections.emptyList();
    }

}