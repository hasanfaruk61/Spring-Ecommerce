package com.laba.Spring.Ecommerce.odev7.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class CreateProductRequestDto {
    private String name;
    private String category;
    private String photoUrl;
    private String description;
    private Double price;
    private Date createDate;
    private Date updateDate;
}
