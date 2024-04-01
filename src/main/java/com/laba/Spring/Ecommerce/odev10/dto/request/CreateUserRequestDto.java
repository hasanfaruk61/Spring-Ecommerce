package com.laba.Spring.Ecommerce.odev10.dto.request;

import lombok.Data;

@Data
public class CreateUserRequestDto {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private boolean premium;
}
