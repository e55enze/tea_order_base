package com.project.teaorderbase.services;

import com.project.teaorderbase.entity.Order;
import com.project.teaorderbase.repository.OrderRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepos orderRepos;

    public Order createOrder(Order order) { return orderRepos.save(order); }
}