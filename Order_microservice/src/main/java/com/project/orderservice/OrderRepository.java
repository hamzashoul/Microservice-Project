package com.project.orderservice;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order,Long> {
    public List<Order> findAll();
    public Order findOrderById(Long id);
}
