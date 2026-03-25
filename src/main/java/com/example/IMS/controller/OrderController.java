package com.example.IMS.controller;

import com.example.IMS.model.Order;
import com.example.IMS.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order placeOrder(@RequestBody Map<String, Object> orderRequest) {
        Long productId = Long.valueOf(orderRequest.get("productId").toString());
        int quantity = Integer.parseInt(orderRequest.get("quantity").toString());
        return orderService.placeOrder(productId, quantity);
    }
}
