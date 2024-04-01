package com.laba.Spring.Ecommerce.odev10.dto.response;

import lombok.Data;
@Data
public class UserResponseDto {
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private boolean premium;
}
