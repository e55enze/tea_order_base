package com.project.teaorderbase.repository;

import com.project.teaorderbase.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepos extends JpaRepository<OrderItem, Long> {

}
