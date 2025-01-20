package com.project.teaorderbase.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.teaorderbase.entity.Order;
import com.project.teaorderbase.entity.OrderData;
import com.project.teaorderbase.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private final ObjectMapper objectMapper;

    @Autowired
    public Consumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "orders", groupId = "new_order")
    public void listen(String message) {
        System.out.println("Received message: " + message);
        try {
            OrderData orderData = objectMapper.readValue(message, OrderData.class);
            System.out.println("Consumer: Сообщение от Producer принято\n" +
                    orderData.toString());
            // Дополнительно: обработка заказа и включение в базу
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
