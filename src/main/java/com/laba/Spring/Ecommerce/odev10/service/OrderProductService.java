package com.laba.Spring.Ecommerce.odev10.service;

import com.laba.Spring.Ecommerce.odev10.entity.Order;
import com.laba.Spring.Ecommerce.odev10.entity.OrderProduct;
import com.laba.Spring.Ecommerce.odev10.repository.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductService {

    private final OrderProductRepository orderProductRepository;

    @Autowired
    public OrderProductService(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    public List<OrderProduct> findAllByOrder(Order order) {
        return orderProductRepository.findAllByOrder(order);
    }
}
