package com.example.IMS.service;

import com.example.IMS.model.Order;
import com.example.IMS.model.Product;
import com.example.IMS.repository.OrderRepository;
import com.example.IMS.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Order placeOrder(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getQuantity() < quantity) {
            throw new RuntimeException("Insufficient stock");
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);

        double totalPrice = product.getPrice() * quantity;
        Order order = new Order(productId, quantity, totalPrice);
        return orderRepository.save(order);
    }
}
