package com.laba.Spring.Ecommerce.odev6.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class ProductResponseDto {
    private Long id;
    private String name;
    private String category;
    private String photoUrl;
    private String description;
    private Double price;
    private Date createDate;
    private Date updateDate;
}
