package com.laba.Spring.Ecommerce.odev6.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class OrderResponseDto {
    private String orderNumber;
    private Date orderDate;
    private String orderDescription;
    private Double totalAmount;
    private Date createDate;
    private Date updateDate;
}
