package com.laba.Spring.Ecommerce.odev10.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
public class Order extends BaseEntity {
    private String orderNumber;
    private Date orderDate;
    private String orderDescription;
    private Double totalAmount;

    @ManyToOne
    private Users users;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderProduct> orderProducts = new HashSet<>();
}
