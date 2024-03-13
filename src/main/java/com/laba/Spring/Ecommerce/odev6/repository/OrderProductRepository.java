package com.laba.Spring.Ecommerce.odev6.repository;

import com.laba.Spring.Ecommerce.odev6.entity.OrderProduct;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends CrudRepository<OrderProduct, Long> {
    @Transactional
    void deleteByOrderId(Long orderId);
}
