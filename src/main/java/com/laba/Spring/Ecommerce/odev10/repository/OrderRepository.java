package com.laba.Spring.Ecommerce.odev10.repository;

import com.laba.Spring.Ecommerce.odev10.entity.Order;
import org.springframework.data.repository.CrudRepository;
public interface OrderRepository extends CrudRepository<Order, Long> {
}
