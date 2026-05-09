package com.example.test_restful.service;

import java.util.List;
import java.util.Optional;
import com.example.test_restful.model.Order;
import com.example.test_restful.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders() { return orderRepository.findAll(); }
    public Optional<Order> getOrderById(Long id) { return orderRepository.findById(id); }

    @Transactional
    public Order createOrder(Order order) { return orderRepository.save(order); }

    public Order updateOrder(Order order) { return orderRepository.save(order); }

    public void deleteOrder(Long id) { orderRepository.deleteById(id); }
}