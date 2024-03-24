package com.laba.Spring.Ecommerce.odev7.repository;

import com.laba.Spring.Ecommerce.odev7.entity.Order;
import com.laba.Spring.Ecommerce.odev7.entity.OrderProduct;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends CrudRepository<OrderProduct, Long> {
    @Transactional
    void deleteByOrderId(Long orderId);

    List<OrderProduct> findAllByOrder(Order order);
}
