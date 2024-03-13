package com.laba.Spring.Ecommerce.odev6.repository;

import com.laba.Spring.Ecommerce.odev6.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
