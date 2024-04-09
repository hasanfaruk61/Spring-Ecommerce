package com.laba.Spring.Ecommerce.odev11.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private String orderProductDesc;
}
