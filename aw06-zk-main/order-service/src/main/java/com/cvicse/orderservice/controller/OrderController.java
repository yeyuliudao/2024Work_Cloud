package com.cvicse.orderservice.controller;

import com.cvicse.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders/{orderId}/products/{productId}")
    public String getOrderDetails(@PathVariable String orderId, @PathVariable String productId) {
        return orderService.getOrderDetails(orderId, productId);
    }
}