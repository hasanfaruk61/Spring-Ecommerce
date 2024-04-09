package com.laba.Spring.Ecommerce.odev11.repository;

import com.laba.Spring.Ecommerce.odev11.entity.Order;
import org.springframework.data.repository.CrudRepository;
public interface OrderRepository extends CrudRepository<Order, Long> {
}
