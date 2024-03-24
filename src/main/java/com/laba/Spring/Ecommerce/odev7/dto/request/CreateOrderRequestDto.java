package com.laba.Spring.Ecommerce.odev7.dto.request;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CreateOrderRequestDto {
    private List<Long> productIds;
    private String orderNumber;
    private Date orderDate;
    private String orderDescription;
    private Double totalAmount;
    private Long userId;
}
