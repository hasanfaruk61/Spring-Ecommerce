package com.laba.Spring.Ecommerce.odev7.dto.request;

import lombok.Data;

@Data
public class CreateUserRequestDto {

    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
}
