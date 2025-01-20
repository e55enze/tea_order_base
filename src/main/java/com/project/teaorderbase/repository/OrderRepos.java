package com.project.teaorderbase.repository;

import com.project.teaorderbase.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepos extends JpaRepository<Order, Long> {

}