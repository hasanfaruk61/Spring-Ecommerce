package com.laba.Spring.Ecommerce.odev6.dto.request;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class CreateOrderRequestDto {
    private Long productId;
    private String orderNumber;
    private Date orderDate;
    private String orderDescription;
    private Double totalAmount;
    private Set<Long> productIds;
}
