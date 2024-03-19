package com.laba.Spring.Ecommerce.odev7.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Users extends BaseEntity{
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
}
