package com.project.teaorderbase.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.teaorderbase.entity.Order;
import com.project.teaorderbase.entity.OrderData;
import com.project.teaorderbase.entity.OrderItem;
import com.project.teaorderbase.entity.User;
import com.project.teaorderbase.services.OrderItemService;
import com.project.teaorderbase.services.OrderService;
import com.project.teaorderbase.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private final ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    public Consumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "orders", groupId = "new_order")
    public Long listen(String message) {
//        System.out.println("Received message: " + message);
        try {
            OrderData orderData = objectMapper.readValue(message, OrderData.class);

            System.out.println("Consumer: Сообщение от Producer принято\n" +
                    orderData.toString());
            User user = new User();
            user.setName(orderData.getName());
            user.setPhone(orderData.getPhone());
            user.setEmail(orderData.getEmail());
            user.setCity(orderData.getCity());
            user.setAddress(orderData.getAddress());

            User savedUser = userService.createUser(user);

            Order order = new Order();
            order.setDateOrder(orderData.getOrderTime());
            order.setPrice(orderData.getPrice());
            order.setUser(savedUser);

            Order savedOrder = orderService.createOrder(order);

            for (OrderData.OrderItem item : orderData.getCartItems()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(savedOrder);
                orderItem.setProduct(item.getProductName());
                orderItem.setProductQuantity(item.getProductQuantity());
                orderItem.setProductPrice(item.getProductPrice());
                orderItemService.createOrderItem(orderItem);
            }

            return savedOrder.getId();

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
