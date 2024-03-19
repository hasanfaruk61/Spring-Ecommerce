package com.laba.Spring.Ecommerce.odev7.repository;

import com.laba.Spring.Ecommerce.odev7.entity.Order;
import org.springframework.data.repository.CrudRepository;
public interface OrderRepository extends CrudRepository<Order, Long> {
}
