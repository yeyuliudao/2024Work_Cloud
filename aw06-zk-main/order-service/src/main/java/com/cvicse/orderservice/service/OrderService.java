package com.cvicse.orderservice.service;

import com.cvicse.orderservice.client.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderService {

    private final ProductClient productClient;

    @Autowired
    public OrderService(ProductClient productClient) {
        this.productClient = productClient;
    }

    public String getOrderDetails(String orderId, String productId) {
        String productInfo = productClient.getProductById(productId);
        return "Order with ID: " + orderId + " contains product: " + productInfo;
    }
}