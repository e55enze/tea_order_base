package com.project.teaorderbase.services;

import com.project.teaorderbase.entity.OrderItem;
import com.project.teaorderbase.repository.OrderItemRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepos orderItemRepos;

    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepos.save(orderItem);
    }
}
